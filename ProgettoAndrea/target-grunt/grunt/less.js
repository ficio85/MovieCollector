module.exports = {
  main: {
    options: {
      style: 'expanded',
      paths: ["less/"],
      // sourceMap: true,
      // sourceMapFilename: "build/application-ie.min.css.map",
      banner: '/*! <%= package.name %> <%= grunt.template.today("yyyy-mm-dd") %> */'
    },
    files: {
      'build/application.css': 'less/application.less'
    }
  },
  ie: {
    options: {
      style: 'expanded',
      paths: ["less/"],
      // sourceMap: true,
      // sourceMapFilename: "build/application-ie.min.css.map",
      banner: '/*! <%= package.name %> <%= grunt.template.today("yyyy-mm-dd") %> */'
    },
    files: {
      'build/application-ie.css': 'less/application-ie.less'
    }
  }
};
