import Vue from 'vue';
import Router from 'vue-router';

import Home from './base/home/home';
import HomePage from './app/components/home-page/home-page';
import ArticlePage from './app/components/article-page/article-page';
import Register from './app/components/register/register';
import Login from './app/components/login/login';
import Settings from './app/components/settings/settings';
import UserSetting from './app/components/settings/user-setting/user-setting';
import BlogList from './app/components/settings/blog-list/blog-list';
import HandleBlog from './app/components/settings/handle-blog/handle-blog';
import EditBlog from './app/components/settings/handle-blog/edit-blog';
import AddBlog from './app/components/settings/handle-blog/add-blog';
Vue.use(Router);

export default new Router({
  // mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '',
      redirect: '/home',
    },
    {
      path: '',
      name: 'home',
      component: Home,
      children: [
        {
          path: 'home',
          name: 'HomePage',
          component: HomePage,
        },
        {
          path: 'article-page',
          name: 'article-page',
          component: ArticlePage,
        },
        {
          path: 'settings',
          name: 'settings',
          component: Settings,
          redirect: '/settings/user-setting',
          children: [
            {
              path: 'user-setting',
              name: 'user-setting',
              component: UserSetting,
            },
            {
              path: 'blog-list',
              name: 'blog-list',
              component: BlogList,
            },
            {
              path: 'handle-blog',
              name: 'bhandle-blog',
              component: HandleBlog,
              children: [
                {
                  path: 'editblog',
                  name: 'editblog',
                  component: EditBlog,
                },
                {
                  path: 'addblog',
                  name: 'addblog',
                  component: AddBlog,
                },
              ],
            },
          ],
        },
      ],
    },
    {
      path: 'register',
      name: 'register',
      component: Register,
    },
    {
      path: 'login',
      name: 'login',
      component: Login,
    },
  ],
});
