<?php
/* @var $this MonitoredUserController */
/* @var $model MonitoredUser */

$this->breadcrumbs=array(
	'Monitored Users'=>array('index'),
	'Create',
);

$this->menu=array(
	array('label'=>'List MonitoredUser', 'url'=>array('index')),
	array('label'=>'Manage MonitoredUser', 'url'=>array('admin')),
);
?>

<h1>Create MonitoredUser</h1>

<?php $this->renderPartial('_form', array('model'=>$model)); ?>