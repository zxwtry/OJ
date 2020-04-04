package comm


// TaskRecordStruct 一个任务
type TaskRecordStruct struct {
	Type int32			// 任务类型
	Title string		// 任务标题
	Desc string			// 任务描述
	MaxCnt int32		// 已完成次数
	JumpURL string		// 跳转链接
}

// TaskRecordList 本地配置的任务列表
type TaskRecordList struct {
	TaskRecords []TaskRecordStruct
}


func YamlTest() {
	
}