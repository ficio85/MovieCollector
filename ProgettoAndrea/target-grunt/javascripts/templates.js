app.templates.errorsHtml = '<div id="alert-error" class="pure-alert pure-alert-error" style="display:none;">'+
                            '<ul></ul>'+
                          '</div>';
app.templates.confirmation =  function(text, styleId) {
  if (!styleId) {
    styleId = "confirmation";
  }
  return '<div id="'+styleId+'" class="hide modal fade static" tabindex="-1" role="dialog" aria-hidden="true">'+text+'</div>';
};
