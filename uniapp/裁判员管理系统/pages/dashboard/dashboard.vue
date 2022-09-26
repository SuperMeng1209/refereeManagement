<template>
	<view>
		<u-modal 
			:show="open" 
			:title="modal.title" 
			:closeOnClickOverlay="true" 
			:content="modal.content" 
			:confirmText="`已读`" 
			:cancelText="`取消`" 
			:showCancelButton="true"
			@confirm="tryToModal(0)"
			@cancel="tryToModal(1)"
			@close="tryToModal(1)"
		>
		</u-modal>
		<uni-card>
			<uni-table stripe emptyText="暂无选派任务">
				<uni-tr>
					<uni-th align="left" style="font-size: x-small">我的选派任务</uni-th>
				</uni-tr>
				<uni-tr v-for="(item, index) in todoList" :key="index">
					<uni-td align="left">{{ item.title }}</uni-td>
				</uni-tr>
			</uni-table>
			<template v-slot:actions>
				<view style="padding-bottom: 10rpx;">
					<uni-pagination :total="todopage.total" :current="todopage.current" pageSize="3" @change="gotoToDoPage" />
				</view>
			</template>
		</uni-card>
		
		<uni-card>
			<uni-table stripe emptyText="暂无消息提醒">
				<uni-tr>
					<uni-th align="left" style="font-size: x-small">我的消息提醒</uni-th>
				</uni-tr>
				<uni-tr v-for="(item, index) in msgList" :key="index">
					<uni-td align="left">
						<text @click="openModal(item.status,item.msgid,item.msg)" v-if="item.status == '未读'" style="color: #F56C6C;">{{ item.msg }}</text>
						<text @click="openModal(item.status,item.msgid,item.msg)" v-else>{{ item.msg }}</text>
					</uni-td>
				</uni-tr>
			</uni-table>
			<template v-slot:actions>
				<view style="padding-bottom: 10rpx;">
					<uni-pagination :total="messagePage.total" :current="messagePage.current" pageSize="3" @change="gotoMessagePage" />
				</view>
			</template>
		</uni-card>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				open: false,
				modal: {
					msgid: '',
					title: '',
					content: ''
				},
				todo:[],
				todoList: [],
				msg: [],
				msgList: [],
				todopage: {
					total: 0,
					current: 0,
					size: 3
				},
				messagePage: {
					total: 0,
					current: 0,
					size: 3
				}
			}
		},
		methods: {
			tryToModal(e){
				if(e == 1){
					this.open = false
				}
				if(e == 0){
					uni.getStorage({
						key: 'user',
						success: (res) =>{
							uni.request({
								url: 'http://'+this.$serviceUrl+':8098/msgApi/updateStatus',
								method: 'POST',
								data:{ userid: res.data.id , msgid: this.modal.msgid },
								success: (res) =>{
									this.open = false
									this.init()
								}
							})
						}
					})
					
				}
			},
			openModal(status,id,msg){
				this.modal.title = status+'消息',
				this.modal.content = msg
				this.modal.msgid = id
				this.open = true
			},
			gotoToDoPage(e){
				if(e.type == 'prev'){
					//上一页
					this.todoList = this.todo.slice((e.current * 3) - 3 ,(e.current * 3))
				}
				if(e.type == 'next'){
					//下一页
					this.todoList = this.todo.slice( ((e.current * 3) - 3) ,(e.current  * 3) )
				}
			},
			gotoMessagePage(e){
				if(e.type == 'prev'){
					//上一页
					this.msgList = this.msg.slice((e.current * 3) - 3 ,(e.current * 3))
				}
				if(e.type == 'next'){
					//下一页
					this.msgList = this.msg.slice( ((e.current * 3) - 3) ,(e.current  * 3) )
				}
			},
			init(){
					uni.getStorage({
						key: 'user',
						success: (res) =>{
							uni.request({
								url: 'http://'+this.$serviceUrl+':8098/toDoApi/findAll',
								method: 'POST',
								data: { userid: res.data.id},
								success: (res) =>{
									this.todo = res.data
									this.todoList = this.todo.slice(0,3)
									this.todopage.total = this.todo.length
									this.todopage.current = 1
								}
							})
							
							uni.request({
								url: 'http://'+this.$serviceUrl+':8098/msgApi/findAll',
								method: 'POST',
								data: { userid: res.data.id },
								success: (res) =>{
									this.msg = res.data
									this.msgList = this.msg.slice(0,3)
									this.messagePage.total = this.msg.length
									this.messagePage.current = 1
								}
							})
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
