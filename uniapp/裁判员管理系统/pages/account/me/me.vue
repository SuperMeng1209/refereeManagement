<template>
  <view class="page">
	<u-modal 
		:show="show" 
		:title="title == 'todo' ? '发布选派任务' : title == 'msg' ? '发布消息提醒' :'' " 
		:closeOnClickOverlay="true" 
		:showCancelButton="true"
		:confirmText="`发送`"
		:cancelText="`取消`" 
		@cancel="closeModal()"
		@close="closeModal()"
		@confirm="tryToSave"
	>
		<u--form labelPosition="left" :model="model1">
			<u-form-item label="姓名" borderBottom>
				<u--input v-model="model1.realname" border="none"></u--input>
			</u-form-item>
			<u-form-item :label="title == 'todo' ? '选派任务信息' : title == 'msg' ? '消息信息' :'' " borderBottom>
				<u--textarea v-model="model1.msg" placeholder="请输入内容" count></u--textarea>
			</u-form-item>
		</u--form>
	</u-modal>
    <view class="top">
      <view class="background"></view>
    </view>
    <view class="user-card">
      <view class="card">
        <view class="top">
          <view class="userImage">
            <image src="../../../static/defaultHeadImg.jpg"></image>
          </view>
        </view>
        <view class="bottom">
          <view class="left">
            <view class="user-text">
              <text>{{meList.realname}}</text>
            </view>
            <view class="user-phone">{{meList.phone}}</view>
          </view>
          <view class="right flex-center">
            <u-icon class="icon" name="arrow-right"></u-icon>
          </view>
        </view>
      </view>
    </view>
    <view class="list-card" v-if="meList.role == 'admin'">
      <view class="card" @click="goto('todo')">
        <view class="item item-bottom-solid">
          <view class="left flex-center">
            <image src="../../../static/myIcon/qiu.png" mode="aspectFit"></image>
          </view>
          <view class="center">
            <text>发送选派任务</text>
          </view>
          <view class="right flex-center">
            <u-icon class="icon" name="arrow-right"></u-icon>
          </view>
        </view>
      </view>
      <view class="card" @click="goto('msg')">
        <view class="item item-bottom-solid">
          <view class="left flex-center">
            <image src="../../../static/myIcon/1.png" mode="aspectFit"></image>
          </view>
          <view class="center">
            <text>发送消息提醒</text>
          </view>
          <view class="right flex-center">
            <u-icon class="icon" name="arrow-right"></u-icon>
          </view>
        </view>
      </view>
    </view>
     <view class="quit flex-center">
        <view class="btn flex-center" @click="quit">
          退出登录
        </view>
     </view>
  </view>
</template>
<style lang="scss" scoped>
.top {
  height: 250rpx;
  position: relative;
  .background {
    background-color: #036a52;
    border-bottom-left-radius: 22px;
    border-bottom-right-radius: 22px;
    position: absolute;
    height: 180rpx;
    width: 100%;
  }
}
.icon {
  color: #96a1ae;
  font-size: 40rpx;
}
.user-card {
  height: 170rpx;
  padding: 0 15px;
  .card {
    position: relative;
    bottom: 62px;
    height: 250rpx;
    background-color: white;
    border-radius: 5px;
    .top {
      height: 30%;
      position: relative;
      .userImage {
        position: absolute;
        bottom: 24%;
        left: 10%;
        width: 150rpx;
        height: 150rpx;
        overflow: hidden;
        border-radius: 50%;
        border: 2px solid white;
		image{
			width: 100%;
			height: 100%;
		}
      }
    }
    .bottom {
      display: flex;
      height: 70%;
      .left {
        width: 80%;
        height: 100%;
        position: relative;
        .user-text {
          width: 100%;
          font-size: 1.6em;
          padding-left: 80rpx;
          height: 50%;
        }
        .user-phone {
          color: #96a1ae;
          padding-left: 80rpx;
          height: 50%;
          width: 100%;
          font-size: 0.9em;
        }
      }
      .right {
        width: 20%;
        height: 50%;
      }
    }
  }
}
.list-card {
  padding: 0 15px;
  .card {
    border-radius: 5px;
    position: relative;
    background-color: white;
    border-radius: 5px;
    padding: 5px 30px;
    .item {
      display: flex;
      height: 120rpx;
      .left {
        width: 15%;
        image {
          width: 70rpx;
          height: 70rpx;
        }
      }
      .center {
        width: 65%;
        display: flex;
        justify-content: start;
        align-items: center;
        font-size: 1.1em;
      }
      .right {
        width: 20%;
        justify-content: flex-end;
      }
    }
  }
}
.item-bottom-solid {
  border-bottom: 1px solid #d4d6da;
}
.quit{
  height: 100rpx;
  margin-top: 50px;
  .btn{
    background-color: #036a52;
    border-radius: 30px;
    width: 80%;
    color: white;
    font-size: 1.2em;
    height: 100%;
  }
}
.flex-center{
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
 
<script>
export default {
  data() {
    return {
		meList: {},
		title: '',
		show: false,
		model1:{
			realname: '',
			msg: ''
		},
	};
  },
  //监听页面初始化，其参数同 onLoad 参数，为上个页面传递的数据，参数类型为 Object（用于页面传参），触发时机早于 onLoad
  onInit() {},
  //监听页面加载，其参数为上个页面传递的数据，参数类型为 Object（用于页面传参）
  onLoad() {
	  
  },
  //监听页面初次渲染完成。注意如果渲染速度快，会在页面进入动画完成前触发
  onReady() {},
  //监听页面显示。页面每次出现在屏幕上都触发，包括从下级页面点返回露出当前页面
  beforeDestroy() {},
  //页面滚动到底部的事件（不是scroll-view滚到底），常用于下拉下一页数据。
  onReachBottom() {},
  onShareAppMessage(res) {},
  created() {},
  methods: {
	  quit(){
		  uni.clearStorage();
		  uni.redirectTo({
		  	url: '/pages/account/login/login'
		  });
	  },
	  goto(e){
		if(e == 'todo'){
			this.title = 'todo',
			this.show = true
		}else if(e == 'msg'){
			this.title = 'msg'
			this.show = true
		}
	  },
	  closeModal(){
		  this.show = false
	  },
	  tryToSave(){
		let _this = this;
		var UniPath = _this.title == 'todo' ? 'toDoApi/addMsg' : _this.title == 'msg' ? 'msgApi/addToMsg': ''
		  uni.request({
		  	url: 'http://'+this.$serviceUrl+':8098/'+ UniPath ,
			method: 'POST',
			data: this.model1,
			success: (res) => {
				if(res.data == -1){
					uni.$u.toast('查询无此人')
				}else if(res.data == 1){
					uni.$u.toast(_this.title == 'todo' ? '选派任务创建成功' : _this.title == 'msg' ? '消息提醒创建成功': '')
					this.show = false
				}
			}
		  })
	  }
  },
  mounted() {
  	uni.getStorage({
  		key: 'user',
  		success: (res) =>{
			console.log(res)
  			uni.request({
  				url: 'http://'+this.$serviceUrl+':8098/userApi/findById',
  				method: "POST",
  				data:{
  					userid: res.data.id
  				},
  				success: (res) =>{
  					this.meList = res.data
  				}
  			})
  		}
  	})
  }
};
</script>


<!-- <template>
	<view>
		<uni-card>
			<view style="font-size: larger;">{{meList.realname}}</view>
			<view style="font-size: small;color: #999999;">{{meList.username}}</view>
		</uni-card>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				meList: {}
			}
		},
		methods: {
			
		},
		mounted() {
			uni.getStorage({
				key: 'user',
				success: (res) =>{
					uni.request({
						url: 'http://'+this.$serviceUrl+':8098/userApi/findById',
						method: "POST",
						data:{
							userid: res.data.userid
						},
						success: (res) =>{
							this.meList = res.data
						}
					})
				}
			})
		}
	}
</script>

<style>

</style>
 -->