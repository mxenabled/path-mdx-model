## Path MDX-Model Upgrade - v2

* Relocates classes to standard package names
* Moves Spring components to mdx-model project (`mdx-model:2.0.0`)
* Cleans out deprecated and unused classes.

### Change Summary

See [Release Notes](https://github.com/mxenabled/path-sdk/blob/master/CHANGELOG.md#100-2022-10-07)

### Update package names

1. In terminal
2. `cd` to repository root
3. Run `curl --header "PRIVATE-TOKEN: glpat-oFE1eP_Mf6GsWU_j68zs" "https://gitlab.mx.com/api/v4/projects/1113/repository/files/upgrades%2Fv35%2Fmdx-web-35-upgrade.sh/raw?ref=master" | bash -s ./ -d false`

### Update to new mdx-web platform

_Example_

```groovy
dependencies {
  implementation platform("com.mx.path-mdx-model:platform:[2.0,3.0)")
}
```

### Potential Issues
