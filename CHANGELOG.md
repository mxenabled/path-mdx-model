# Changelog

## [3.0.0](https://github.com/mxenabled/path-mdx-model/compare/v2.2.1...v3.0.0) (2023-05-16)


### ⚠ BREAKING CHANGES

* apply v2 upgrade script
* removing deprecated code to fix build

### Bug Fixes

* adding MdxBase, MdxList, changing models base class ([7da31d5](https://github.com/mxenabled/path-mdx-model/commit/7da31d5bc01dcefd11226e8390f80fc469628a80))
* adding to upgrade script ([adb1f43](https://github.com/mxenabled/path-mdx-model/commit/adb1f435b5ca4bf8f78453691d1239961c43bcc8))
* removing deprecated code to fix build ([7da31d5](https://github.com/mxenabled/path-mdx-model/commit/7da31d5bc01dcefd11226e8390f80fc469628a80))


### Code Refactoring

* apply v2 upgrade script ([a5425a9](https://github.com/mxenabled/path-mdx-model/commit/a5425a9b605016e5f13a1cbdcd18c35727c04225))

## [2.2.1](https://github.com/mxenabled/path-mdx-model/compare/v2.2.0...v2.2.1) (2023-04-24)


### Bug Fixes

* Add account model relation to cross account transfers ([995d5f9](https://github.com/mxenabled/path-mdx-model/commit/995d5f9564521004f25fa71f1f7fbf3a65ead160))

## [2.2.0](https://github.com/mxenabled/path-mdx-model/compare/v2.1.0...v2.2.0) (2023-04-04)


### Features

* **web:** add before initialization hook to SpringMdxGatewayManagerTest ([bb40d6b](https://github.com/mxenabled/path-mdx-model/commit/bb40d6b8608846d0983a45a18ab3a98949fd1eeb))
* **web:** expose configurator observer ([607b9a7](https://github.com/mxenabled/path-mdx-model/commit/607b9a7dc8f88c18c269ebf77bd6dad91cc1c295))

## [2.1.0](https://github.com/mxenabled/path-mdx-model/compare/v2.0.0...v2.1.0) (2023-03-30)


### Features

* realtime - deprecated Request objects, added connection ([9cd52ca](https://github.com/mxenabled/path-mdx-model/commit/9cd52ca9ac0f5ad3953b6e04942cdf9f714718f2))


### Bug Fixes

* fixing breaking change ([9cd52ca](https://github.com/mxenabled/path-mdx-model/commit/9cd52ca9ac0f5ad3953b6e04942cdf9f714718f2))
* only throw on 401, check status in onProcess ([9cd52ca](https://github.com/mxenabled/path-mdx-model/commit/9cd52ca9ac0f5ad3953b6e04942cdf9f714718f2))
* removing breaking change, fixing constructor ([9cd52ca](https://github.com/mxenabled/path-mdx-model/commit/9cd52ca9ac0f5ad3953b6e04942cdf9f714718f2))
* restoring old tests ([9cd52ca](https://github.com/mxenabled/path-mdx-model/commit/9cd52ca9ac0f5ad3953b6e04942cdf9f714718f2))

## [2.0.0](https://github.com/mxenabled/path-mdx-model/compare/v1.8.1...v2.0.0) (2023-03-29)


### ⚠ BREAKING CHANGES

* remove deprecated classes and methods
* move models and accessors to new package
* move controllers to new package
* move RepositoryFilter and GatewayManager
* move MdxRequestContextFilter
* move HmacFilter
* clean up filter package paths
* move RequireAuthenticationFilter
* move SessionFilter
* move ErrorHandlerFilter

### Features

* add gateway content pre-processor ([2f1da5d](https://github.com/mxenabled/path-mdx-model/commit/2f1da5d727832e6c2e8832279490f26265f51e6c))
* add gateway manager ([2f1da5d](https://github.com/mxenabled/path-mdx-model/commit/2f1da5d727832e6c2e8832279490f26265f51e6c))
* move controllers to new package ([ec0b2c7](https://github.com/mxenabled/path-mdx-model/commit/ec0b2c74bf777c79e5e1d23018793a2c1faf2bfc))
* move ErrorHandlerFilter ([9440808](https://github.com/mxenabled/path-mdx-model/commit/944080858937db0cfcd0170a57758c0e7879d351))
* move HmacFilter ([98022b1](https://github.com/mxenabled/path-mdx-model/commit/98022b174fc37069d66ced399121b9f377b7a36b))
* move MdxRequestContextFilter ([4c430e8](https://github.com/mxenabled/path-mdx-model/commit/4c430e8d9415b8790a5f0966bf88ad61306e2258))
* move RepositoryFilter and GatewayManager ([2f1da5d](https://github.com/mxenabled/path-mdx-model/commit/2f1da5d727832e6c2e8832279490f26265f51e6c))
* move RequireAuthenticationFilter ([45d8e32](https://github.com/mxenabled/path-mdx-model/commit/45d8e3294c5c8ae8c38e17ff9902f3a5a6a01598))
* move SessionFilter ([21ba00b](https://github.com/mxenabled/path-mdx-model/commit/21ba00b6392295cac56f9976f2dd7c21532c081f))
* move ValidationExceptionHandler and FilterOrderSequence ([df443eb](https://github.com/mxenabled/path-mdx-model/commit/df443eb84ef0601d78f95c325459e93a96b2b59e))


### Bug Fixes

* clean up filter package paths ([bea5708](https://github.com/mxenabled/path-mdx-model/commit/bea5708d862068ebca382d548efd7fba0ae672b3))
* correct PathToolsTest package ([e3a4b8d](https://github.com/mxenabled/path-mdx-model/commit/e3a4b8d064a1c598c4d8596bf80d4e188e7b1c4a))
* correct the package paths in v2 upgrade script ([a41b9ef](https://github.com/mxenabled/path-mdx-model/commit/a41b9ef4b8992a60b3d00503981f35f5291103be))


### Code Refactoring

* move models and accessors to new package ([d6c678e](https://github.com/mxenabled/path-mdx-model/commit/d6c678e724ef6179fba727e1d647e165b9cc0584))
* remove deprecated classes and methods ([1f4a988](https://github.com/mxenabled/path-mdx-model/commit/1f4a988283442149204ae166db9ea7bfbc8f84f0))

## [1.8.1](https://github.com/mxenabled/path-mdx-model/compare/v1.8.0...v1.8.1) (2023-03-28)


### Features

* Add pending function to TransactionsBaseAccessor and pending endpoint to AccountsController ([f3107ac](https://github.com/mxenabled/path-mdx-model/commit/f3107ac3297c71afcdf100c8419a0d1c1a6f616d))

## [1.8.0](https://github.com/mxenabled/path-mdx-model/compare/v1.7.1...v1.8.0) (2023-02-01)


### Features

* use session.current to get user id in UserIdProvider ([8656b5a](https://github.com/mxenabled/path-mdx-model/commit/8656b5a24467d32deff655c443a42f1fa828a772))

## [1.7.1](https://github.com/mxenabled/path-mdx-model/compare/v1.7.0...v1.7.1) (2023-01-17)


### Bug Fixes

* removing MdxApiExceptions ([1591917](https://github.com/mxenabled/path-mdx-model/commit/159191770044940bef99b8ab4e5c870d53ac0ba9))
* rework ([1591917](https://github.com/mxenabled/path-mdx-model/commit/159191770044940bef99b8ab4e5c870d53ac0ba9))

## [1.7.0](https://github.com/mxenabled/path-mdx-model/compare/v1.6.1...v1.7.0) (2022-12-09)


### Features

* set transfer fee controllers to use query parameters ([ae08dc9](https://github.com/mxenabled/path-mdx-model/commit/ae08dc9d0ebbba888c66a90d4173ada7ee269e81))

## [1.6.1](https://github.com/mxenabled/path-mdx-model/compare/v1.6.0...v1.6.1) (2022-12-06)


### Bug Fixes

* added serialization for fromAccountId and toAccountId ([04fb086](https://github.com/mxenabled/path-mdx-model/commit/04fb08639b9c195acc1edcd9ee2574de29e71619))

## [1.6.0](https://github.com/mxenabled/path-mdx-model/compare/1.5.0...v1.6.0) (2022-11-18)


### Features

* create mdx-web module ([2fa621a](https://github.com/mxenabled/path-mdx-model/commit/2fa621aaa49c99f11196fa44201cec299bdd7a59))

## [1.5.0](https://github.com/mxenabled/path-mdx-model/compare/1.4.0...1.5.0) (2022-11-03)


### Features

* publish platform ([c976c86](https://github.com/mxenabled/path-mdx-model/commit/c976c86d477649edf3d71ef2ebc7195415c6214e))
* publish to central ([577e4d2](https://github.com/mxenabled/path-mdx-model/commit/577e4d2a04d42636c00b1b273fb2041b2a810ef2))

## [1.4.0](https://github.com/mxenabled/path-mdx-model/compare/v1.3.1...1.4.0) (2022-10-28)


### Features

* publish sources and javadocs artifacts ([c8e131c](https://github.com/mxenabled/path-mdx-model/commit/c8e131c4ecbadff7e7ebca94da1dcc8f3ca7c99d))

## [1.3.1](https://github.com/mxenabled/path-mdx-model/compare/v1.3.0...v1.3.1) (2022-10-26)


### Bug Fixes

* make statement_closed_on LocalDate ([518c925](https://github.com/mxenabled/path-mdx-model/commit/518c925280588bedad026c272850644d055e62f7))

## [1.3.0](https://github.com/mxenabled/path-mdx-model/compare/v1.2.0...v1.3.0) (2022-10-25)


### Features

* add statement_closed_on to Account ([78cfd8a](https://github.com/mxenabled/path-mdx-model/commit/78cfd8a876bab43c394b82e7bda012f0ec89e63f))

## [1.2.0](https://github.com/mxenabled/path-mdx-model/compare/1.1.0...v1.2.0) (2022-10-24)


### Features

* Add title field to MfaChallengeQuestion ([#18](https://github.com/mxenabled/path-mdx-model/issues/18)) ([36465ff](https://github.com/mxenabled/path-mdx-model/commit/36465ff0f2226093be47092f1a6b6cd798de7299))
