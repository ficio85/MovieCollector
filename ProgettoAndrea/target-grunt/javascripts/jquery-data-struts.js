(function($) {
  $.fn.dataStruts = function(key, value) {
    var classValue = this.attr('class');
    if (value === undefined) {
      if (key === undefined) {
        // get values from data-* and class
        if (classValue !== undefined) {
          var values = classValue.split(/\s+/);
          var datas = {};
          $.each(values, function(){
            var keyValue = this.split('==');
            if (keyValue.length === 2) {
              datas[keyValue[0]] = keyValue[1];
            }
          });
          this.data(datas);
        }
        return this.data();
      } else {
        if (typeof key === "object"){
          // set values
          return this.data(key);
        } else if (typeof key === "string") {
          var valueKey = this.data(key);
          if (valueKey !== undefined) {
              // get the value from data-*
              return valueKey;
          } else if (classValue !== undefined) {
            // get the value from class and set to data-* and return data-*
            var v = getDataClass(key, classValue);
            this.data(key, v);
            this.attr("data-"+key, v);
            return this.data(key);
          }
        }
      }
    } else {
      // set the value
      return this.data(key, value);
    }
    return;
  };

  /**
    permette di utilizzare le classi html per passare dei parametri chiave/valore con
    la sintassi class="chiave==valore chiave==valore altraclasse etc"
    la seguente funzione permett di leggere il valore di una chiave
  **/
  var getDataClass = function(key, classValue) {
    var values = classValue.split(/\s+/);
    var value;
    $.each(values, function(){
      var keyValue = this.split('==');
      if (keyValue.length === 2 && keyValue[0] === key) {
        value = keyValue[1];
        return false;
      }
    });
    return value;
  };

})(jQuery);
