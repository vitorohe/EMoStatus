<?php
/* @var $this MonitoredUserController */
/* @var $data MonitoredUser */
?>

<div class="view">

	<b><?php echo CHtml::encode($data->getAttributeLabel('id')); ?>:</b>
	<?php echo CHtml::link(CHtml::encode($data->id), array('view', 'id'=>$data->id)); ?>
	<br />

	<b><?php echo CHtml::encode($data->getAttributeLabel('username')); ?>:</b>
	<?php echo CHtml::encode($data->username); ?>
	<br />

	<b><?php echo CHtml::encode($data->getAttributeLabel('full_name')); ?>:</b>
	<?php echo CHtml::encode($data->full_name); ?>
	<br />

	<b><?php echo CHtml::encode($data->getAttributeLabel('description')); ?>:</b>
	<?php echo CHtml::encode($data->description); ?>
	<br />

	<b><?php echo CHtml::encode($data->getAttributeLabel('monitoring_activated')); ?>:</b>
	<?php echo CHtml::encode($data->monitoring_activated); ?>
	<br />

	<b><?php echo CHtml::encode($data->getAttributeLabel('monit_skype_activated')); ?>:</b>
	<?php echo CHtml::encode($data->monit_skype_activated); ?>
	<br />

	<b><?php echo CHtml::encode($data->getAttributeLabel('monit_record_activated')); ?>:</b>
	<?php echo CHtml::encode($data->monit_record_activated); ?>
	<br />

	<?php /*
	<b><?php echo CHtml::encode($data->getAttributeLabel('alert_sms_activated')); ?>:</b>
	<?php echo CHtml::encode($data->alert_sms_activated); ?>
	<br />

	<b><?php echo CHtml::encode($data->getAttributeLabel('alert_email_activated')); ?>:</b>
	<?php echo CHtml::encode($data->alert_email_activated); ?>
	<br />

	*/ ?>

</div>