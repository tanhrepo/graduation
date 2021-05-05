<template>
  <div>
    <div v-for="(item,index) in itemData" :key="index">
      <MessageItem :ItemData="item"></MessageItem>
    </div>
  </div>
</template>

<script>
import MessageItem from "@/views/message/components/MessageItem";
import {getCommentList} from "@/api/system/article";

export default {
  name: "MineComment",
  props: ["userInfo"],
  watch: {
    'userInfo': {
      deep: true,
      handler (val) {
        console.log("val",val)
        this.getData()
      }
    }
  },
  components: {MessageItem},
  data(){
    return{
      itemData: [
        {
          id: 19,
          createUser: {
            avatar: "/profile/avatar/2021/04/27/055f29d6-9b63-4fd9-9481-a242fbf86d6a.jpeg",
            nickName: "若依",
            userId: 1,
            userName: "admin",
          },
          createBy: "admin",
          content: "而我如果你看到就给你看决定是否",
          answerUser: 1,
          articleId: 5,
          createTime: "2021-05-04 13:15:59",
          imgs: [
            "http://localhost:8080/profile/upload/2021/05/04/aaf0d35e-7622-4713-8ff4-7dad9b78ea4b.png",
            "http://localhost:8080/profile/upload/2021/05/04/b1790500-c6c1-4150-bdd0-79568795d50c.png",
            "http://localhost:8080/profile/upload/2021/05/04/e1cd311a-6e9e-45c1-aed4-688c0aec1f7d.png",
          ],
        },
      ]
    }
  },
  methods: {
    // 数据获取
    getData() {
      let data = {
        createBy: this.userInfo.userName
      }
      return new Promise((resolve, reject) => {
        getCommentList(data).then(res => {
          this.itemData = res.rows
          console.log(res.rows)
        }).catch(error => {
          reject(error)
        })
      })
    },
  }
}
</script>

<style scoped>

</style>