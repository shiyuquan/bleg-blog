import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import './registerServiceWorker';
import 'font-awesome/css/font-awesome.min.css';
import Element from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './mock';
import mavonEditor from 'mavon-editor';
import 'mavon-editor/dist/css/index.css';
import moment from 'moment';

Vue.config.productionTip = false;
Vue.use(mavonEditor);
Vue.use(Element);
new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');

Vue.filter('timeToDay', (time: any) => {
  return moment(time).get('date');
});

Vue.filter('timeToMonth', (time: any) => {
  return moment(time).get('month') + 1;
});

Vue.filter('timeFormate', (time: any) => {
  return moment(time).format('YYYY-MM-DD');
});
