<template>
	<view>
		<uni-card style="margin-top: 10px">
			<u--form abelPosition="left" :model="meList" labelWidth="70">
				<u-form-item label="真实姓名" borderBottom >
					<u--input v-model="meList.realname" disabled border></u--input>
				</u-form-item>
				<u-form-item label="身高" borderBottom >
					<u--input v-model="meList.cm" border></u--input>
				</u-form-item>
				<u-form-item label="体重" borderBottom >
					<u--input v-model="meList.kg" border></u--input>
				</u-form-item>
				<u-form-item label="联系方式" borderBottom >
					<u--input v-model="meList.phone" border></u--input>
				</u-form-item>
				<u-form-item label="证书编号" borderBottom >
					<u--input v-model="meList.certificateNum" border></u--input>
				</u-form-item>
				<u-form-item label="证书等级" borderBottom >
					<u--input v-model="meList.certificateLeave" border></u--input>
				</u-form-item>
			</u--form>
			<u-button type="primary" text="提交修改" @click="tryToUpdateUser"></u-button>
		</uni-card>
	</view>
</template>

<script>
	export default{
		data(){
			return {
				meList: {
					
				}
			}
		},
		methods: {
			tryToUpdateUser(){
				uni.request({
					url: 'http://'+this.$serviceUrl+':8098/userApi/updateById',
					method:"POST",
					data: this.meList,
					success: (res) => {
						if(res.data == 1){
							uni.$u.toast('修改成功')
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
	}
	
</script>

<style>
</style>