def call(def params = null) {
    stage("Serenity") {
        container("build") {
            publishHTML(target: [
                    reportName           : 'Serenity',
                    reportDir            : 'target/site/serenity',
                    reportFiles          : 'index.html',
                    keepAll              : true,
                    alwaysLinkToLastBuild: true,
                    allowMissing         : false
            ])
        }
    }
}

return this
