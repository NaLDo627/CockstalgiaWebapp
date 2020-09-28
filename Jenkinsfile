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
             /*app.push("${env.BUILD_NUMBER}")*/
             app.push("latest")
         }
     }

     post {
        unsuccessful {
            discordSend description: '', footer: '', image: '', link: 'env.BUILD_URL', result: 'UNSTABLE|FAILURE|ABORTED', thumbnail: '', title: 'env.JOB_NAME failed!!', webhookURL: 'https://discordapp.com/api/webhooks/757641684866564286/bUBzIkSqol9Wc7VF23aMdLvkNamQgA7SDoiaXkx2jY8beue5qtRKqvbDQ4N0MJt73EZJ'
        }
     }
 }
