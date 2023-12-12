import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '@/views/index/Index.vue'
import Post from "@/views/index/Post.vue"
import Chat from "@/views/index/Chat.vue";
import Post2 from "@/views/index/Post2.vue"
import Email from "@/views/index/EmailSend.vue"
Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'index',
        redirect: '/post/single',
        component: Index,
        children: [
            {path: '/myInfo', name: 'MyInfo', component: () => import('../views/MySpace.vue')},
            {path: '/password', name: 'Password', component: () => import('../views/Password.vue')},
            {path: '/noticeManagement', name: 'noticeManagement', component: () => import('../views/NoticeManagement.vue')},
            {path: '/noticeBoard', name: 'noticeBoard', component: () => import('../views/NoticeBoard.vue')},
            {
                path: '/post/single',
                name: 'post',
                component: Post
            },
            {
                path: '/post/love',
                name: 'post2',
                component: Post2
            },
            {
                path: '/chat',
                name: 'chat',
                component: Chat
            },
            {
                path: '/email',
                name: 'email',
                component: Email
            }
        ]
    },
    {path: '/login', name: 'Login', component: () => import('../views/Login.vue')},
    {path: '/register', name: 'Login', component: () => import('../views/Register.vue')},


    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (about.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    // }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: routes
})

const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (to) {
  return VueRouterPush.call(this, to).catch(err => err)
}
export default router
