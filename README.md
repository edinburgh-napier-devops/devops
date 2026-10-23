# SET09803 2026-7 TR1 001 - DevOps
* Master Build Status ![Develop Build Status](https://img.shields.io/github/actions/workflow/status/edinburgh-napier-devops/devops/main.yml?branch=master&style=flat-square)
* Develop Branch Status ![Develop Build Status](https://img.shields.io/github/actions/workflow/status/edinburgh-napier-devops/devops/main.yml?branch=develop&style=flat-square)
* License [![LICENSE](https://img.shields.io/github/license/edinburgh-napier-devops/devops.svg?style=flat-square)](https://github.com/edinburgh-napier-devops/devops/blob/master/LICENSE)
* Release [![Releases](https://img.shields.io/github/release/edinburgh-napier-devops/devops/all.svg?style=flat-square)](https://github.com/edinburgh-napier-devops/devops/releases)

## Team 2

- [David Andrew](https://github.com/oba-David)
- [Hanna Hirenka](https://github.com/Hirenka1)
- [Indika Pradeep Ahangama Vithanage](https://github.com/indikapradeep-40833265)
- [Kingsley Forbes](https://github.com/KingMan242)
- [Steve Abraham](https://github.com/Stevie279)
- [Tim Regan-Morris](https://github.com/treganmorris)

## Report Evidence

`Met` is confirmed only when the report has been run and its output captured. `Pending verification` means an implementation exists but its required output screenshot has not yet been committed. `No` means the report is not implemented on this branch. Screenshot links are shown only for files actually present in `reports/`.

| ID | Name | Met | Screenshot |
|---|---|---|---|
| 1 | All the countries in the world organised by largest population to smallest. | Yes | [report-01-countries-world.png](reports/report-01-countries-world.png) |
| 2 | All the countries in a continent organised by largest population to smallest. | Pending verification | — |
| 3 | All the countries in a region organised by largest population to smallest. | Pending verification | — |
| 4 | The top N populated countries in the world where N is provided by the user. | Pending verification | — |
| 5 | The top N populated countries in a continent where N is provided by the user. | Pending verification | — |
| 6 | The top N populated countries in a region where N is provided by the user. | Pending verification | — |
| 7 | All the cities in the world organised by largest population to smallest. | No | — |
| 8 | All the cities in a continent organised by largest population to smallest. | Pending verification | — |
| 9 | All the cities in a region organised by largest population to smallest. | Pending verification | — |
| 10 | All the cities in a country organised by largest population to smallest. | Pending verification | — |
| 11 | All the cities in a district organised by largest population to smallest. | Pending verification | — |
| 12 | The top N populated cities in the world where N is provided by the user. | Pending verification | — |
| 13 | The top N populated cities in a continent where N is provided by the user. | No | — |
| 14 | The top N populated cities in a region where N is provided by the user. | Pending verification | — |
| 15 | The top N populated cities in a country where N is provided by the user. | Yes | [report-15-top-cities-country.png](reports/report-15-top-cities-country.png) |
| 16 | The top N populated cities in a district where N is provided by the user. | Pending verification | — |
| 17 | All the capital cities in the world organised by largest population to smallest. | Pending verification | — |
| 18 | All the capital cities in a continent organised by largest population to smallest. | Pending verification | — |
| 19 | All the capital cities in a region organised by largest population to smallest. | Pending verification | — |
| 20 | The top N populated capital cities in the world where N is provided by the user. | Pending verification | — |
| 21 | The top N populated capital cities in a continent where N is provided by the user. | Pending verification | — |
| 22 | The top N populated capital cities in a region where N is provided by the user. | Pending verification | — |
| 23 | The population of people, people living in cities, and people not living in cities in each continent. | Pending verification | — |
| 24 | The population of people, people living in cities, and people not living in cities in each region. | Pending verification | — |
| 25 | The population of people, people living in cities, and people not living in cities in each country. | Pending verification | — |
| 26 | The population of the world. | No | — |
| 27 | The population of a continent. | Yes | [report-27-population-continent.png](reports/report-27-population-continent.png) |
| 28 | The population of a region. | No | — |
| 29 | The population of a country. | No | — |
| 30 | The population of a district. | No | — |
| 31 | The population of a city. | No | — |
| 32 | The number of people who speak Chinese, English, Hindi, Spanish and Arabic, from greatest number to smallest, including the percentage of the world population. | No | — |


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
