[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.mx.path-mdx-model/mdx-models/badge.svg)](https://search.maven.org/search?q=com.mx.path-mdx-model)
![img](https://img.shields.io/badge/semver-2.0.0-green)
[![Conventional Commits](https://img.shields.io/badge/Conventional%20Commits-1.0.0-%23FE5196?logo=conventionalcommits&logoColor=white)](https://conventionalcommits.org)

Issues: https://github.com/mxenabled/path-core/issues

# Path - MDX Model

This repository contains 4 projects:

* mdx-models
* mdx-gateways
* realtime

## mdx-models

This contains the data model and accessor definitions for [MDXv6](https://developer.mx.com/drafts/mdx/overview/#what-is-helios).

Also, see [Path Documentation](https://docs.mx.com/path-sdk#overview_getting_started)

## mdx-gateway-generator

This is an interim project that pulls together the assembled mdx-models and the SDK gateway-generator. This is used in `mdx-gateways` as the annotationProcessor. This project is not published, only used for internal build process.

## mdx-gateways

This project defines the MDX base gateway used as the launching point for the annotation processor to generate the gateways based on the definitions in `mdx-models`

## realtime

This is an API used to interact with MDX realtime.

## Usage

### Using platform (preferred)

_Gradle_
<!-- x-release-please-start-version -->
```groovy
dependencies {
  api platform("com.mx.path-mdx-model:platform:4.9.0")

  implementation "com.mx.path-mdx-model:mdx-models"
  implementation "com.mx.path-mdx-model:mdx-gateways"
  implementation "com.mx.path-mdx-model:realtime"
}
```
<!-- x-release-please-end -->

### Using without platform

_Gradle_
<!-- x-release-please-start-version -->
```groovy
dependencies {
  implementation "com.mx.path-mdx-model:mdx-models:4.9.0"
  implementation "com.mx.path-mdx-model:mdx-gateways:4.9.0"
  implementation "com.mx.path-mdx-model:realtime:4.9.0"
}
```
<!-- x-release-please-end -->

## Contributing

Commits should conform to the [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) specification (not enforced yet).

#### Clone the repository

`git clone git@github.com:mxenabled/path-mdx-model.git`

`cd path-mdx-model`

#### Install git conventional commit tools (optional)

This will install commitizen and commitlint to help ensure your commits are formatted correctly before you push them up to Github:

`bin/setup`

(To remove commitizen and githooks use `bin/reset`)

#### Contribute changes

To contribute changes:

  1. create a feature branch off of master or the current release branch (`git checkout -b feature/name_of_feature`)
  2. Commit changes to branch (use `git cz` for help with conventional commit)
  3. Push branch up `git push origin master`
  4. create a pull request
