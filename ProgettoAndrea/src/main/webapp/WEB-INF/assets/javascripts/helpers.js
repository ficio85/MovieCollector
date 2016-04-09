/**
  Helpers and template
  **/
var app = { helpers: {}, templates: {}, models: {} };
app.models.Place = { getUrlProvincia: "/CoopApp-WEB/ProvinciaJSON" };

// crea data da una stringa
app.helpers.creaData = function(dataStringa) {
  if (app.helpers.isBlank(dataStringa)) {
    throw "Data non presente";
  }
  var arrayData = dataStringa.split(/\D/).compact();
  if (arrayData.length !== 3 || arrayData[2].length !== 4 || arrayData[1].length !== 2 || parseInt(arrayData[1]) > 12 || arrayData[0].length !== 2 || parseInt(arrayData[0]) > 31) {
    throw "Formato Data non corretto: "+dataStringa;
  }
  try {
    return new Date(arrayData[2], arrayData[1]-1, arrayData[0]);
  } catch(e) {
    throw "Formato Data non corretto: "+dataStringa;
  }
};

// serializza una form
app.helpers.serializeForm = function(elem) {
  var $elem = $(elem);
  var elements = $elem.find('[name]');
  var serialized = { length: 0 };
  elements.each(function() {
    if (!app.helpers.isBlank(this.id)) {
      var $this = $(this);
      serialized[this.id] = "";
      if ($this.attr('type') === "checkbox" || $this.attr('type') === "radio") {
        if ($this.is(":checked")) {
          serialized[this.id] = $this.val();
          serialized.length += 1;
        }
      } else {
        serialized[this.id] = $this.val();
        serialized.length += 1;
      }
    }
  });
  return serialized;
};

// verifica che una variabile sia undefined o null

app.helpers.isBlank = function(elem) {
  if (typeof elem === "undefined" || elem === null || (typeof elem === "string" && $.trim(elem) === "")) {
    return true;
  }
  return false;
};

app.helpers.$label = function(elem) {
  return $('label[for="'+elem+'"]').eq(0);
};

app.helpers.label = function(elem, asterisk) {
  var label = app.helpers.$label(elem).eq(0).text();
  if (asterisk) {
    return label;
  }
  return label.replace("*","");
};

app.helpers.validation = function(elem) {
  var $this = $(elem);
  var labels = [];
  var errors = [];
  $this.find(".atleastone").each(function() {
    if (!app.helpers.isBlank($(this).val())){
      labels.empty();
      return false;
    }
    labels.push(app.helpers.label(this.id));
  });
  
  if(labels.length > 0){
    errors.push('<li>Almeno uno dei seguenti campi deve essere valorizzato: '+labels.join(', ')+'</li>');
  }
  $this.find('.required').each(function() {
    var $this = $(this);
    if (app.helpers.isBlank($this.val())){
      errors.push('<li>'+app.helpers.label(this.id)+': Campo Obbligatorio</li>');
    }
  });
  $this.find('.datepicker').each(function() {
    if (!app.helpers.isBlank(this.value)) {
      try {
        app.helpers.creaData(this.value);
      } catch(e) {
        errors.push('<li>'+app.helpers.label(this.id)+': '+e+'</li>');
      }
    }
  });
  $this.find('.minutes').each(function() {
    if (!app.helpers.isBlank(this.value)) {
      var minutes = parseInt(this.value);
      if (minutes > 59 || minutes < 0 || this.value.match(/\D/g)) {
        errors.push('<li>'+app.helpers.label($(this).parent().find('.hours').attr('id'))+': Il campo minuti deve essere un numero compreso tra 0 e 59</li>');
      }
    }
  });
  $this.find('.hours').each(function() {
    if (!app.helpers.isBlank(this.value)) {
      if (parseInt(this.value) < 0 || this.value.match(/\D/g)) {
        errors.push('<li>'+app.helpers.label(this.id)+': Il campo ore deve essere un numero maggiore o uguale a 0</li>');
      }
    }
  });
  $('.datepicker.end').each(function() {
    var startDate = $(this).parent().prev().find('.datepicker.start');
    try {
      if (app.helpers.creaData(this.value) < app.helpers.creaData(startDate.val())) {
        errors.push('<li>'+app.helpers.label(this.id)+': Deve essere maggiore o uguale di '+app.helpers.label(startDate.attr('id'))+'</li>');
      }
    } catch(e) {}
  });
  $('.rangeSuperiore:checked').each(function() {
    var $this = $(this);
    var inizio = $this.dataStruts("start");
    var fine = $this.dataStruts("end");
    var sup = parseInt($this.dataStruts("sup"));
    try {
      var dataInizio = app.helpers.creaData($(inizio).val());
      var dataFine = app.helpers.creaData($(fine).val());
      if (dataFine < dataInizio.nthDate(sup)) {
        errors.push('<li>'+app.helpers.label(this.id)+': Il periodo di assenza deve essere maggiore di '+sup+' giorni, oppure bisogna rimuovere la selezione a questo campo.</li>');
      }
    } catch(e) {}
  });
  app.helpers.genericValidator(errors);
  return errors.length === 0;
};

app.helpers.genericValidator = function(errors) {
  if (errors.length > 0) {
    var $ul = $('.messages').html(app.templates.errorsHtml).find('#alert-error').hide('fade').find('ul').empty();
    for (var i = 0; i < errors.length; i++) {
      $ul.append(errors[i]);
    }
    $('#alert-error').show('fade', function() {
      $('html, body').animate({scrollTop:0}, 'slow');
    });
  }
};

jQuery.fn.createDialog = function(body, title) {
  if (app.helpers.isBlank(body)) {
    if (app.helpers.isBlank(this.dataStruts('dialog-body'))) {
      body = "";
    } else {
      body = this.dataStruts('dialog-body');
    }
  }
  if (app.helpers.isBlank(title)) {
    if (app.helpers.isBlank(this.dataStruts('dialog-title'))) {
      title = "";
    } else {
      title = this.dataStruts('dialog-title');
    }
  }
  var $this;
  if (app.helpers.isBlank(this.dataStruts('dialog'))) {
    $this = this;
  } else {
    $this = $(this.dataStruts('dialog'));
  }
  $this.find('.modal-body').html(body);
  $this.find('.modal-title').text(title);
  
  if (!app.helpers.isBlank(this.dataStruts('dialog-button'))) {
    var self = this;
    $this.find('.modal-footer').html('<button type="button" class="btn btn-default" data-dismiss="modal">Chiudi</button><button class="button-submit btn btn-default" type="button" class="btn btn-default" data-dismiss="modal">'+this.dataStruts('dialog-button')+'</button>');
    $('.button-submit').on('click', function() {
      self.off('click').click();
    });
  } else {
    $this.find('.modal-footer').html('<button type="button" class="btn btn-default" data-dismiss="modal">Chiudi</button>');
  }
	  
  $this.modal({
	show: true,
    backdrop: true
  });
  return this;
};

jQuery.fn.setHeight = function() {
  var panelHeight = 0;
  this.each(function() {
    var currentHeight = $(this).height();
    if (currentHeight > panelHeight) {
      panelHeight = currentHeight;
    }
  });
  this.height(panelHeight);
  return this;
};
