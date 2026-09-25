# SET09803 2026-7 TR1 001 - DevOps
* Master Build Status ![Develop Build Status](https://img.shields.io/github/actions/workflow/status/edinburgh-napier-devops/devops/main.yml?branch=master&style=flat-square)
* Develop Branch Status ![Develop Build Status](https://img.shields.io/github/actions/workflow/status/edinburgh-napier-devops/devops/main.yml?branch=develop&style=flat-square)
* License [![LICENSE](https://img.shields.io/github/license/edinburgh-napier-devops/devops.svg?style=flat-square)](https://github.com/edinburgh-napier-devops/devops/blob/master/LICENSE)
* Release [![Releases](https://img.shields.io/github/release/edinburgh-napier-devops/devops/all.svg?style=flat-square)](https://github.com/edinburgh-napier-devops/devops/releases)

## Team 2

- [oba-David](https://github.com/oba-David)
- [Hanna Hirenka](https://github.com/hannah8890)
- [Indika Pradeep Ahangama Vithanage](https://github.com/indikapradeep-40833265)
- [Kingsley Forbes](https://github.com/KingMan242)
- [Steve Abraham](https://github.com/Stevie279)
- [Tim Regan-Morris](https://github.com/treganmorris)

## Development workflow

The team follows the Gitflow process introduced in [Lab 02](https://github.com/Kevin-Sim/SET09803-DevOps-Global-Online/tree/master/labs/lab02):

- `master` contains released, production-ready versions.
- `develop` integrates completed features for the next release.
- `feature/*` branches contain isolated work and are created from `develop`.
- `release_*` branches prepare a working version for release to `master`.
- `hotfix/*` branches contain urgent fixes created from `master`.

For each feature and release, the team will:

1. Pull the latest `develop` branch.
2. Create a feature branch from `develop`.
3. Implement the feature and package the application JAR.
4. Build and test the Docker configuration through GitHub Actions.
5. Merge the latest `develop` changes into the feature branch.
6. Confirm that the updated feature branch still builds successfully.
7. Open a pull request and merge the reviewed feature into `develop`.
8. Repeat the feature cycle until the release is ready.
9. Create a release branch from `develop`, then merge it into `master`.
10. Tag and publish the release, then merge the release branch back into `develop`.
