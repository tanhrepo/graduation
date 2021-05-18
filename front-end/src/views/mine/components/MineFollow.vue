<template>
  <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="我的关注" name="first">
      <div v-for="(item, index) in itemData" :key="index">
        <FollowItem :itemData="item"></FollowItem>
      </div>
    </el-tab-pane>
    <el-tab-pane label="关注我的" name="second">
      <div v-for="(item, index) in followData" :key="index">
        <FollowItem :itemData="item"></FollowItem>
      </div>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
import { mapState } from 'vuex'
import { getFollowList } from '@/api/system/operation'
import { getIdUserInfo } from '@/api/login'
import FollowItem from '@/views/mine/components/FollowItem'

export default {
  name: 'MineFollow',
  components: { FollowItem },
  data() {
    return {
      itemData: [],
      followData: [],
      activeName: 'first'
    }
  },
  computed: {
    ...mapState(['user'])
  },
  mounted() {
    this.getDataF()
  },
  methods: {
    // 重置
    reset() {
      this.itemData = []
    },
    // 数据获取
    getData() {
      this.reset()
      let data = {
        followId: this.user.userInfo.userId
      }
      return new Promise((resolve, reject) => {
        getFollowList(data)
          .then(res => {
            for (let i = 0, j = res.rows.length; i < j; i++) {
              this.getUserInfo(res.rows[i].userId)
            }
          })
          .catch(error => {
            reject(error)
          })
      })
    },
    getUserInfo(id) {
      return new Promise((resolve, reject) => {
        getIdUserInfo(id)
          .then(res => {
            this.itemData.push(res.data)
            console.log('this.itemData', this.itemData)
          })
          .catch(error => {
            reject(error)
          })
      })
    },
    // 数据获取
    getDataF() {
      let data = {
        userId: this.user.userInfo.userId
      }
      return new Promise((resolve, reject) => {
        getFollowList(data)
          .then(res => {
            for (let i = 0, j = res.rows.length; i < j; i++) {
              this.getUserInfoF(res.rows[i].followId)
            }
          })
          .catch(error => {
            reject(error)
          })
      })
    },
    getUserInfoF(id) {
      return new Promise((resolve, reject) => {
        getIdUserInfo(id)
          .then(res => {
            this.followData.push(res.data)
            console.log('followData', this.followData)
          })
          .catch(error => {
            reject(error)
          })
      })
    },
    handleClick(tab, event) {
      console.log(tab, event)
    }
  }
}
</script>

<style scoped></style>
