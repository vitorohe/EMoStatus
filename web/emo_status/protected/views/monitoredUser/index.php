<?php
/* @var $this MonitoredUserController */
/* @var $dataProvider CActiveDataProvider */

$this->breadcrumbs=array(
	'Monitored Users',
);

$this->menu=array(
	array('label'=>'Create MonitoredUser', 'url'=>array('create')),
	array('label'=>'Manage MonitoredUser', 'url'=>array('admin')),
);
?>

<h1>Monitored Users</h1>

<?php $this->widget('zii.widgets.CListView', array(
	'dataProvider'=>$dataProvider,
	'itemView'=>'_view',
)); ?>
