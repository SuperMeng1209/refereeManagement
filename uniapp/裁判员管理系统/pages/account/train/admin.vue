<template>
	<view>
		<u-modal
			:show="show" 
			:title="`创建培训`" 
			:closeOnClickOverlay="true" 
			:showCancelButton="true"
			:confirmText="`创建`"
			:cancelText="`取消`" 
			@cancel="closeModal()"
			@close="closeModal()"
			@confirm="tryToSave"
		>
			<u--textarea v-model="model1.trainContent" placeholder="请输入培训内容" count></u--textarea>
		</u-modal>
		<u-modal
			:show="trainShow" 
			:title="`培训信息`" 
			:closeOnClickOverlay="true" 
			:showCancelButton="true"
			:confirmText="status == 1 ? `评价并结束`: null"
			:cancelText="`取消`" 
			@cancel="closeTrainModal()"
			@close="closeTrainModal()"
			@confirm="tryToSaveTrain"
		>
			<u--textarea v-model="isPass" placeholder="请输入评价" count :disabled="status == 0"></u--textarea>
		</u-modal>
		<uni-card style="margin-top: 10px">
			<view style="width: 150rpx;">
				<u-button type="primary" text="创建培训" @click="openModal"></u-button>
			</view>
			<view class="u-page">
				<u-list>
					<u-list-item v-for="(item, index) in indexList" :key="index">
						<u-cell :title="item.status == 1 ? '[进行中]'+item.trainContent : '[已结束]'+item.trainContent" @click="tryToTrain(item.status,item.trainid,item.trainContent)"></u-cell>
					</u-list-item>
				</u-list>
			</view>
		</uni-card>
	</view>
</template>

<script>
	export default{
		prop: ['meList'],
		data(){
			return {
				show: false,
				trainShow: false,
				model1: {
					trainContent: '',
					indexList: []
				},
				indexList:[],
				content: '',
				status: '',
				id: '',
				isPass: ''
			}
		},
		methods: {
			tryToSaveTrain(){
				if(this.status == 1){
					uni.request({
						url: 'http://'+this.$serviceUrl+':8098/trainApi/pass',
						method: "POST",
						data:{
							trainid: this.id,
							isPass: this.isPass
						},
						success: (res) => {
							if(res.data == 1){
								uni.$u.toast('评价完成')
								this.closeTrainModal()
								this.init()
							}
						}
					})
				}else{
					uni.$u.toast('该培训已结束')
				}
			},
			tryToTrain(status,id,content){
				this.content = content
				this.status = status
				this.id = id
				this.trainShow = true
			},
			closeTrainModal(){
				this.content = ""
				this.status = ""
				this.id = ""
				this.trainShow = false
			},
			tryToSave(){
				uni.request({
					url: 'http://'+this.$serviceUrl+':8098/trainApi/addTrain',
					method:"POST",
					data:this.model1,
					success: (res) => {
						if(res.data == 1){
							uni.$u.toast('创建成功')
							this.init()
							this.closeModal()
						}
					}
				})
			},
			openModal(){
				this.show = true
			},
			closeModal(){
				this.show = false
			},
			init(){
				uni.request({
					url: 'http://'+this.$serviceUrl+':8098/trainApi/findAllList',
					method: "POST",
					success: (res) => {
						this.indexList = res.data
					}
				})
			}
		},
		mounted() {
			this.init()
		}
	}
</script>

<style>
</style>