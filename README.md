# Clojure Playground

This project contains my explorations of the clojure language.

## Running the code

This repository does not contain an application, only
namespaces with my understanding about several topics.

You can, however, run the unit tests with:

```shell
bin/kaocha

# Watch for changes
bin/kaocha --watch

# Exit at first failure
bin/kaocha --fail-fast

# Only run the `unit` suite
bin/kaocha unit

# Only run a single test
bin/kaocha --focus my.app.foo-test/bar-test

# Use an alternative config file
bin/kaocha --config-file tests_ci.edn

# See all available options
bin/kaocha --test-help
```

The [tests.edn](./tests.edn) file configures how Kaocha will
be executed. See [Kaocha Doc][kaocha] for more details.

## Namespaces

Each namespace contains a different exploration/exercise:

* `code-arcade` - solutions to exercises from [Code Signal
  Arcade][code-arcade].
* `hacker-rank` - solutions to exercises from [Hacker Rank]
  [hacker-rank].
* `tdd` - katas from [TDD Manifesto Katas][tdd-katas].
* `spec` - my studies on [clojure.spec][clojure.spec].

## Unit Tests

I'm a huge advocate of unit tests, so all my exercises are
tested!

There are some exercises that are purely in the test namespace:

* `destructuring` - my understandings about [destructuring]
  [destructuring] in test format.
*

[code-arcade]: https://app.codesignal.com/arcade

[hacker-rank]: https://www.hackerrank.com/

[tdd-katas]: https://tddmanifesto.com/exercises/

[clojure.spec]: https://clojure.org/guides/spec

[destructuring]: https://clojure.org/guides/destructuring

[kaocha]: https://github.com/lambdaisland/kaocha
