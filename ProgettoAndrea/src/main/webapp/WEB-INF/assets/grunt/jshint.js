module.exports = {
  // define the files to lint
  files: ['javascripts/*.js', 'test/**/test.js'],
  // configure JSHint (documented at http://www.jshint.com/docs/)
  options: {
      // more options here if you want to override JSHint defaults
    globals: {
      jQuery: true,
      $: true,
      console: true,
      module: true,
      app: true,
      window: true,
      document: true
    },
    curly: true,
    //camelcase: true,
    bitwise: true,
    eqeqeq: true,
    forin: true,
    //immed: true,
    nonbsp: true,
    //indent: 2,
    latedef: "nofunc",
    undef: true,
    unused: false,
    devel: true,
    browser: true
  }
};
