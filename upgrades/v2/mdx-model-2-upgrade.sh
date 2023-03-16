#!/bin/bash

# Fixes an illegal byte sequence issue on MacOS
export LC_ALL=C

function usage {
  echo "Usage: $0 [directory] [-d]"
  echo
  echo "Where:"
  echo "   -d perform a dry run (default: true)"
  echo
  echo "Example 1 (dry run):"
  echo "   $0 /some/cool/directory"
  echo "Example 2 (live run):"
  echo "   $0 /some/cool/directory -d false"
  exit 0
}

if [ -z "$1" ]; then
  usage
else
  ROOT_DIR=$1
  shift
fi

DRY_RUN=true

while getopts ":d:" OPTION; do
  case $OPTION in
  d)
    if [ "$OPTARG" = false ] || [ "$OPTARG" = 0 ]; then
      DRY_RUN=false
    elif [ "$OPTARG" = true ] || [ "$OPTARG" = 1 ]; then
      DRY_RUN=true
    else
      echo "Invalid option passed to -d: $OPTARG"
      echo -e "Acceptable values are: true, false, 1, 0\n"
      usage
      exit 1
    fi
    ;;
  \?)
    usage
    exit 1
    ;;
  :)
    usage
    exit 1
    ;;
  esac
done

if $DRY_RUN; then
  echo "Doing a dry run. No files will be modified."
else
  echo "Doing a live run. Files may be modified."
fi

# Find & Replace all imports
function process_file {
  local model_version="[2.0,3.0)"

  export nl=$'\n'
  local file=$1
  local java_mappings=(
    #"com.mx.accessors.AccessorConfiguration([^a-zA-Z])=com.mx.common.accessors.AccessorConfiguration\1"
    #"([^a-zA-Z])YamlSerializer([^a-zA-Z])=\1ObjectMapYamlDeserializer\2"
    #"([^a-zA-Z])JsonObjectMapDeserializer([^a-zA-Z])=\1ObjectMapJsonDeserializer\2"

    "com\.mx\.models\.=com.mx.path.model.mdx.model."
    "com\.mx\.accessors\.=com.mx.path.model.mdx.accessor."

    "UserIdProvider\.setUserIdProvider=UserIdProvider.setUserIdProvider ** REMOVED **"
  )
  local groovy_mappings=(
    "JUNK=JUNK"
    #"com.mx.accessors.AccessorConfiguration([^a-zA-Z]?)=com.mx.common.accessors.AccessorConfiguration\1"
    #"([^a-zA-Z])YamlSerializer([^a-zA-Z]?)=\1ObjectMapYamlDeserializer\2"
    #"package com.mx.accessors([_a-zA-Z\.]*)([^_a-zA-Z\.])$=package com.mx.accessors\1\2\nimport com.mx.common.exception.AccessorMethodNotImplementedException\nimport com.mx.common.gateway.GatewayAPI\nimport com.mx.common.accessors.API\nimport com.mx.common.accessors.AccessorConfiguration\nimport com.mx.common.accessors.Accessor\nimport com.mx.common.accessors.RootAccessor\nimport com.mx.common.accessors.AccessorResponse\nimport com.mx.common.accessors.PathResponseStatus"

    "com\.mx\.models\.=com.mx.path.model.mdx.model."
    "com\.mx\.accessors\.=com.mx.path.model.mdx.accessor."


    "UserIdProvider\.setUserIdProvider=UserIdProvider.setUserIdProvider ** REMOVED **"
  )
  local gradle_mappings=(
    "([\"\'])com\.mx\.path-mdx-model:platform:[^\"\']+([\"\'])=\1com.mx.path-mdx-model:platform:$model_version\2"
  )
  local gateway_mappings=(
    "JUNK=JUNK"
  )

  local reported_file=false
  local mappings=("${global_mappings[@]}")

  if [ "${file: -5}" == ".java" ]; then
    mappings=("${mappings[@]}" "${java_mappings[@]}")
  elif [ "${file: -7}" == ".groovy" ] || [ "${file: -3}" == ".kt" ]; then
    mappings=("${mappings[@]}" "${groovy_mappings[@]}")
  elif [ "${file: -12}" == "build.gradle" ]; then
    mappings=("${mappings[@]}" "${gradle_mappings[@]}")
  elif [ "${file: -11}" == "gateway.yml" ] || [ "${file: -12}" == "gateway.yaml" ]; then
    mappings=("${mappings[@]}" "${gateway_mappings[@]}")
  else
    echo "Skipping non-code file $file"
  fi

  for pair in "${mappings[@]}"; do
    local from="${pair%%=*}"
    local to="${pair#*=}"

    changes="$(sed <"$file" -E -n "s/$from/$to/gp")"
    if [ -n "$changes" ]; then
      if ! $reported_file; then
        echo
        echo "Found match(es) in file: $file"
        reported_file=true
      fi
      echo "Changing \"$from\" to \"$to\", resulting in:"
      echo "$changes"
      if ! $DRY_RUN; then
        sed -E -i "" "s/$from/$to/g" "$file"
      fi
    fi
  done
  if $reported_file; then
    echo
    echo
  fi
}

### Driver
export -f process_file
export DRY_RUN
find "$ROOT_DIR" -type f \( -iname "*.java" -or -iname "*.groovy" -or -iname "*.kt" -or -iname "build.gradle" -or -iname "gateway.yml" -or -iname "gateway.yaml" \) -exec bash -c 'process_file "$0"' {} \;
exit 0
