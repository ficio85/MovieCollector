module.exports = function(grunt) {
  //  misura il tempo di ciascun task
  require('time-grunt')(grunt);
  grunt.initConfig({
    gruntMavenProperties: grunt.file.readJSON('grunt-maven.json')
  });
  require('load-grunt-config')(grunt);
};
