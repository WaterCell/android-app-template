#!/bin/bash -xe
function publish_report () {
    if [ "$TRAVIS_PULL_REQUEST" = "false" ]; then
        set +x
        echo "machine github.com" >> ~/.netrc
        echo "login $GH_LOGIN"    >> ~/.netrc
        echo "password $GH_TOKEN" >> ~/.netrc
        set -x

        git config --global user.email 'travis@travis-ci.org'
        git config --global user.name 'travis'
        git clone --quiet --branch=gh-pages "https://github.com/$GH_BUILD_REPORT.git" gh-pages
        cd gh-pages

        git rm -q -r "$TRAVIS_BRANCH" || true
        mkdir -p "$TRAVIS_BRANCH"

	pwd

        [ -d ./app/build/test-report ] && cp -a ./app/build/test-report "$TRAVIS_BRANCH"
        git add "$TRAVIS_BRANCH"

        grep "$TRAVIS_BRANCH" branch-list || echo "$TRAVIS_BRANCH" >> branch-list
        git add branch-list

        git commit -q -m "Automatically updated by Travis build $TRAVIS_BUILD_NUMBER"
        git push origin gh-pages

        rm -v ~/.netrc
    fi
}

"$@"
