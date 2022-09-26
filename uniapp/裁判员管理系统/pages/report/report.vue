<template>
	<view class="nav">
		<uni-nav-bar
			dark 
			:fixed="true" 
			shadow 
			background-color="#036a52" 
			status-bar 
			left-icon="left" 
			left-text="首页"
			title="赛事报告" 
			@clickLeft="leftClick" 
		/>
		<uni-card style="margin-top: 10px">
			<u-list>
				<u-list-item v-for="(item, index) in indexList" :key="index">
					<u-cell :title="item.matchName">
						<u-avatar slot="icon" shape="square" size="35" :src="item.base" customStyle="margin: -3px 5px -3px 0"></u-avatar>
					</u-cell>
				</u-list-item>
			</u-list>
		</uni-card>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				indexList: []
			}
		},
		methods: {
			leftClick(){
				uni.redirectTo({
					url: '/pages/dashboard/index'
				});
			},
			init(){
				uni.request({
					url: 'http://'+this.$serviceUrl+':8098/newsApi/findAll',
					method: 'POST',
					success: (res) => {
						console.log(res)
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

<style lang="scss" scoped>
.nav{
	margin-top: -20px;
}
</style>
