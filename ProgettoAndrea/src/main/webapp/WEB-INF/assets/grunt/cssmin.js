module.exports = function(grunt, options) {

  return {
    options: {
      banner: '/*! <%= package.name %> <%= grunt.template.today("yyyy-mm-dd") %> */',
      keepSpecialComments: 0
    },
    css:{
      src: 'build/application.css',
      dest: 'build/application.min.css'
    },
    cssie:{
        src: 'build/application-ie.css',
        dest: 'build/application-ie.min.css'
    }
  }
};
