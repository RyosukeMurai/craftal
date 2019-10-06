// register modal component
Vue.component('modal', {
  template: '#modal-template'
});

var app = new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!'
  }
});