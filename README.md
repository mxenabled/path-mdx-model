[![Release](https://jitpack.io/v/mxenabled/path-mdx-model.svg)](https://jitpack.io/p/mxenabled/path-mdx-model)

Issues: https://github.com/mxenabled/path-sdk/issues

# Path - MDX Model

This repository contains 4 projects:

* mdx-models
* mdx-gateway-generator
* mdx-gateways
* realtime

## mdx-models

This contains the data model and accessor definitions for [MDXv6](https://developer.mx.com/drafts/mdx/overview/#what-is-helios).

## mdx-gateway-generator

This is an interim project that pulls together the assembled mdx-models and the SDK gateway-generator. This is used in `mdx-gateways` as the annotationProcessor. This project is not published, only used for internal build process.

## mdx-gateways

This project defines the MDX base gateway used as the launching point for the annotation processor to generate the gateways based on the definitions in `mdx-models`

## realtime

This is an API used to interact with MDX realtime.

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
