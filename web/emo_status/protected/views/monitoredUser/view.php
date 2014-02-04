<?php
/* @var $this MonitoredUserController */
/* @var $model MonitoredUser */

$this->breadcrumbs=array(
	'Monitored Users'=>array('index'),
	$model->id,
);

$this->menu=array(
	array('label'=>'List MonitoredUser', 'url'=>array('index')),
	array('label'=>'Create MonitoredUser', 'url'=>array('create')),
	array('label'=>'Update MonitoredUser', 'url'=>array('update', 'id'=>$model->id)),
	array('label'=>'Delete MonitoredUser', 'url'=>'#', 'linkOptions'=>array('submit'=>array('delete','id'=>$model->id),'confirm'=>'Are you sure you want to delete this item?')),
	array('label'=>'Manage MonitoredUser', 'url'=>array('admin')),
);
?>

<h1>View MonitoredUser #<?php echo $model->id; ?></h1>

<?php $this->widget('zii.widgets.CDetailView', array(
	'data'=>$model,
	'attributes'=>array(
		'id',
		'username',
		'full_name',
		'description',
		'monitoring_activated',
		'monit_skype_activated',
		'monit_record_activated',
		'alert_sms_activated',
		'alert_email_activated',
	),
)); ?>
