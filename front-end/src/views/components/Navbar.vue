<template>
  <div class="fe-navbar fe-flex-between fe-align-center">
    <div class="fe-align-center">
      <h1 class="fe-flex fe-align-center mr-50">
        <svg class="icon fe-logo" aria-hidden="true">
          <use xlink:href="#icon-wind"></use>
        </svg>
        <span>风物社区</span>
      </h1>

      <el-menu :default-active="activeIndex" mode="horizontal" @select="handleSelect">
        <template v-for="item in menus">
          <el-submenu
              v-if="item.children && !item.meta.hideSubMenu"
              :key="item.path"
              :index="item.path"
              :disabled="item.meta.disabled"
          >
            <template slot="title">{{ item.meta.title }}</template>
            <MenuList :MenuList="item.children"></MenuList>
          </el-submenu>
          <el-menu-item v-else-if="!item.meta.isHidden" :key="item.name" :index="item.name">{{
              item.meta.title
            }}
          </el-menu-item>
        </template>
      </el-menu>
    </div>

    <div class="nav-right">
      <el-input @keyup.enter.native="getSearch(searchItem)" v-model="searchItem">
        <i slot="suffix" @click="getSearch(searchItem)" class="el-input__icon el-icon-search"></i>
      </el-input>
      <el-dropdown class="h-100">
      <span class="el-dropdown-link user-item fe-flex-center">

        <img :src="avatar || baseAvatar" class="user-img" alt="">
      </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="jump">修改资料</el-dropdown-item>
          <el-dropdown-item :hide-on-click="false" @click.native="logout" divided>退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import store from "@/store";
import {mapState, mapMutations} from 'vuex'
import MenuList from './MenuList'
import {deleteAction} from "@/api/manage"
import Services from '@/api/services'
import Cookies from 'js-cookie'
import {removeToken} from '@/utils/auth'

const loginOut = Services.login.loginOut

export default {
  name: 'Navbar',
  components: {
    MenuList
  },
  data() {
    return {
      activeIndex: '',
      menus: [],
      searchItem:'',
      avatar:store.getters.avatar,
      baseAvatar:"@/assets/images/user/user_11.jpg"
    }
  },
  watch: {
    $route() {
      this.setActiveIndex()
    }
  },
  computed: {
    ...mapState('user', {
      userInfo: state => state.userInfo
    }),
  },
  created() {
    this.setActiveIndex()
    console.log('this.$router', this.$router)

    this.menus = this.$router.options.routes.filter(r => r.path !== '/' && r.children)
  },
  methods: {
    ...mapMutations('user', ['setUserInfo']),
    // 搜索
    getSearch(item){
      console.log(item)
    },
    setActiveIndex() {
      const Matched = this.$route.matched
      this.activeIndex = Matched.length >= 2 ? this.getActiveIndex() : this.$route.name
    },
    getActiveIndex() {
      const Matched = this.$route.matched
      let data = getChildren(this.$router.options.routes, Matched[0].name)
      let name

      function getChildren(data, name) {
        return data.find(ele => ele.name === name)
      }

      for (let i = 1; i <= Matched.length - 1; i++) {
        name = Matched[i].name
        if (data.children) {
          if (!data.meta.hideSubMenu) {
            data = getChildren(data.children, name)
          }
        }
      }
      return data.name
    },
    handleSelect(index) {
      if (index !== this.activeIndex) {
        this.activeIndex = index
        this.$router.push({name: index})
      }
    },
    // 退出登录
    async logout() {
      this.$confirm('确定注销并退出系统吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.dispatch('LogOut').then(() => {
          location.href = '/index';
        })
      })
    },
    jump(){
      this.$router.push({name:"userItem"})
    }
  }
}
</script>

<style lang="scss">
.fe-navbar {
  height: 60px;
  width: 1000px;
  margin: 0 auto;
  color: $menu_font_color;
  z-index: 2000;
  transform: translateX(-8px);

  .user-item {
    width: 80px;
    height: 100%;
    cursor: pointer;

    &:hover{
      background-color: #F5F5F5;
    }
    .user-img {
      width: 45px;
      height: 45px;
      border-radius: 50%;
    }
  }

  h1 {
    font-size: 16px;
    line-height: 24px;
    font-weight: 500;
    white-space: nowrap;

    span:last-child {
      margin-left: 1em;
    }
  }

  .el-menu-item{
    transition: 0s;
  }
  .el-menu--horizontal > .el-menu-item.is-active{
    border-bottom: 0;
    font-size: 18px;
  }

  .fe-navbar-user {
    color: #fff;
    cursor: pointer;

    span {
      margin-left: 4px;
    }
  }

  .nav-right{
    display: flex;
    align-items: center;
    height: 100%;

    .el-input{
      margin-right: 6px;
    }
    .el-input__inner{
      border-radius: 16px;
    }
    .el-input__icon{
      cursor: pointer;

      &:hover{
        font-size: 16px;
      }
    }

  }
}

.fe-logo {
  font-size: 24px;
}
</style>