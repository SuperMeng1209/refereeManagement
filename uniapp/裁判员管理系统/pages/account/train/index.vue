<template>
	<view>
		<view v-if="meList.role == 'admin'">
			<ad :meList="meList"></ad>
		</view>
		<view v-if="meList.role == 'caipanyuan'">
			<cpy :meList="meList"></cpy>
		</view>
	</view>
</template>

<script>
	import ad from "@/pages/account/train/admin.vue";
	import cpy from "@/pages/account/train/caipanyuan.vue";
	export default {
		components: {
			ad,
			cpy
		},
		data(){
			return{
				role: '',
				meList: {}
			}
		},
		mounted() {
			uni.getStorage({
				key: 'user',
				success: (res) =>{
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