 node {
     def app

     stage('Clone repository') {
         /* Let's make sure we have the repository cloned to our workspace */

         checkout scm
     }

     stage('Build image') {
         /* This builds the actual image; synonymous to
         * docker build on the command line */

         app = docker.build("hygoogi/cockstalgia-webapp")
     }

     /*stage('Test image') {
         app.inside {
             sh 'echo "Tests passed"'
         }
     }*/

     stage('Push image') {
         /* Finally, we'll push the image with two tags:
         * First, the incremental build number from Jenkins
         * Second, the 'latest' tag.
         * Pushing multiple tags is cheap, as all the layers are reused. */
         docker.withRegistry('https://registry.hub.docker.com', 'docker-hub') {
             app.push("${env.BUILD_NUMBER}")
             app.push("latest")
         }
     }


     stage('Run image') {
        environment {
            DATASOURCE_URL = credentials('spring-datasource-url')
            DATASOURCE_USERNAME = credentials('spring-datasource-username')
            DATASOURCE_PASSWORD = credentials('spring-datasource-password')
            DATASOURCE_DRIVER = credentials('spring-datasource-driver')
        }

      docker.image('hygoogi/cockstalgia-webapp').withRun("-p 8080:8080 -e 'spring.datasource.url=$DATASOURCE_URL' -e 'spring.datasource.username=$DATASOURCE_USERNAME' -e 'spring.datasource.password=$DATASOURCE_PASSWORD' -e 'spring.datasource.driver-class-name=$DATASOURCE_DRIVER' --name cockstalgia-webapp --rm") {c ->
         sh 'echo "Run successfully"'
       }
     }
 }
