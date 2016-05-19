$(function(){
  $('.dropdown')
    .dropdown()
  ;
  $('.ui.radio.checkbox')
    .checkbox()
  ;
  $('.ui.form')
  .form({
    fields: {
      title     : 'empty',
      location  : 'empty',
      personNum : 'empty',
      author    : 'empty',
      info      : 'empty',
      detail    : 'empty'
      // password : ['minLength[6]', 'empty'],
      // skills   : ['minCount[2]', 'empty'],
      // terms    : 'checked'
    }
  });
})
