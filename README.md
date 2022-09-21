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
