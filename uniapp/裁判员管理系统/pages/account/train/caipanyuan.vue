<template>
	<view>
		<u-modal 
		:show="show" 
		:title="`培训信息`" 
		:closeOnClickOverlay="true" 
		:showCancelButton="true"
		:content="chooseContent"
		:confirmText="`报名`"
		:cancelText="`取消`" 
		@cancel="closeModal()"
		@close="closeModal()"
		@confirm="tryToSave"
	>
	</u-modal>
		<view class="u-page">
			<u-list>
				<u-list-item v-for="(item, index) in indexList" :key="index">
					<u-cell :title="item.status == 1 ? '[进行中]'+item.trainContent : '[已结束]'+item.trainContent" @click="tryToTrain(item.trainid,item.trainContent,item.isPass)"></u-cell>
				</u-list-item>
			</u-list>
		</view>
	</view>
</template>

<script>
	export default {
		prop: ['meList'],
		data(){
			return {
				indexList: [],
				show: false,
				chooseId: 0,
				chooseContent: '',
				userid: 0
			}
		},
		methods: {
			tryToSave(){
				uni.request({
					url: 'http://'+this.$serviceUrl+':8098/trainApi/train',
					method:"POST",
					data: {
						trainid: this.chooseId,
						userid: this.userid
					},
					success: (res) => {
						if(res.data == -1){
							uni.$u.toast('该培训未开始或已结束')
						}else if(res.data == -2){
							uni.$u.toast('该培训已报名')
						}else{
							uni.$u.toast('报名完成')
						}
					}
				})
			},
			tryToTrain(id,content,isPass){
				this.show = true
				this.chooseContent = content+'\r\n'+'管理员评价：'+isPass
				this.chooseId = id
			},
			closeModal(){
				this.show = false
				this.chooseContent = ""
				this.chooseId = 0
			}
		},
		mounted() {
			uni.request({
				url: 'http://'+this.$serviceUrl+':8098/trainApi/findAllList',
				method: "POST",
				success: (res) => {
					this.indexList = res.data
				}
			})
			uni.getStorage({
				key: 'user',
				success: (res) =>{
					this.userid = res.data.id
				}
			})
		}
	}
</script>

<style>
</style>