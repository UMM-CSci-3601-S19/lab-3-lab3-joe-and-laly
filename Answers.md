1) There are .gitignore files in the server and client directories in addition to one at the top level. These specifiy files to ignore within these directories.

2) There are multiple build.gradle because it needs to build a client build then a server build. In the top level build.gradle it takes those two build.gradles and makes a build off of them.

3) Server.java accomplishes redirecting an empty string and '/' to the local host and it does this by a request and response. The app.routes.ts accomplishes association of component files with a url path.

4) The user-list.service.ts performs get requests, while user-list.component.ts does filtering of users.
