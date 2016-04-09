module.exports = function(grunt) {
  return {
    maven: {
      files: [grunt.config("gruntMavenProperties.directoryToWatch")+'/javascripts/**', grunt.config("gruntMavenProperties.directoryToWatch")+'/less/**', grunt.config("gruntMavenProperties.directoryToWatch")+'/images/**', '!'+grunt.config("gruntMavenProperties.directoryToWatch")+'/**/CVS/**'],
      tasks: 'default'
    }
  };
}

