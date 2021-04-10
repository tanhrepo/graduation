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
          <el-menu-item v-else :key="item.name" :index="item.name">{{
              item.meta.title
            }}
          </el-menu-item>
        </template>
      </el-menu>
    </div>
  </div>
</template>

<script>
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
      menus: []
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
    handleLogout() {
      deleteAction(loginOut).then(() => {
        this.$confirm('是否确定退出登录?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: '退出登录成功!'
          })
          this.setUserInfo({
            account: '',
            role: '',
          })
          // 清空缓存
          sessionStorage.clear()
          localStorage.clear()
          // 清空cookie
          removeToken()
          Cookies.remove('username')
          Cookies.remove('password')
          Cookies.remove('rememberMe')
          // 重定向到登录页
          this.$router.replace('/login')
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消退出登录'
          })
        })
      })
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

  h1 {
    font-size: 16px;
    font-weight: 500;
    white-space: nowrap;

    span:last-child {
      margin-left: 1em;
    }
  }

  .fe-navbar-user {
    color: #fff;
    cursor: pointer;

    span {
      margin-left: 4px;
    }
  }
}

.fe-logo {
  font-size: 24px;
}
</style>