module.exports = {
  options: {
    banner: '/*! <%= package.name %> <%= grunt.template.today("yyyy-mm-dd") %> */',
    //compress: {
    //  drop_console: true
    //},
    sourceMap: true
  },
  js: {
    src: 'build/application.js',
    dest: 'build/application.min.js'
  }
};
