module.exports = {
	main: {
	    files: [
	      // includes files within path
	      {expand: true, cwd: 'node_modules/bootstrap-less/', src: ['fonts/*.*'], dest: 'build/', filter: 'isFile'},

	
//	      // makes all src relative to cwd
//	      {expand: true, cwd: 'path/', src: ['**'], dest: 'dest/'},
//	
//	      // flattens results to a single level
//	      {expand: true, flatten: true, src: ['path/**'], dest: 'dest/', filter: 'isFile'},
	    ],
	  },
};
