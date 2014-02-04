<?php
/* @var $this MonitoredUserController */
/* @var $model MonitoredUser */
/* @var $form CActiveForm */
?>

<div class="form">

<?php $form=$this->beginWidget('CActiveForm', array(
	'id'=>'monitored-user-form',
	// Please note: When you enable ajax validation, make sure the corresponding
	// controller action is handling ajax validation correctly.
	// There is a call to performAjaxValidation() commented in generated controller code.
	// See class documentation of CActiveForm for details on this.
	'enableAjaxValidation'=>false,
)); ?>

	<p class="note">Fields with <span class="required">*</span> are required.</p>

	<?php echo $form->errorSummary($model); ?>

	<div class="row">
		<?php echo $form->labelEx($model,'username'); ?>
		<?php echo $form->textField($model,'username',array('size'=>45,'maxlength'=>45)); ?>
		<?php echo $form->error($model,'username'); ?>
	</div>

	<div class="row">
		<?php echo $form->labelEx($model,'full_name'); ?>
		<?php echo $form->textField($model,'full_name',array('size'=>45,'maxlength'=>45)); ?>
		<?php echo $form->error($model,'full_name'); ?>
	</div>

	<div class="row">
		<?php echo $form->labelEx($model,'description'); ?>
		<?php echo $form->textArea($model,'description',array('rows'=>6, 'cols'=>50)); ?>
		<?php echo $form->error($model,'description'); ?>
	</div>

	<div class="row">
		<?php echo $form->labelEx($model,'monitoring_activated'); ?>
		<?php echo $form->checkbox($model,'monitoring_activated'); ?>
		<?php echo $form->error($model,'monitoring_activated'); ?>
	</div>

	<div class="row">
		<?php echo $form->labelEx($model,'monit_skype_activated'); ?>
		<?php echo $form->checkbox($model,'monit_skype_activated'); ?>
		<?php echo $form->error($model,'monit_skype_activated'); ?>
	</div>

	<div class="row">
		<?php echo $form->labelEx($model,'monit_record_activated'); ?>
		<?php echo $form->checkbox($model,'monit_record_activated'); ?>
		<?php echo $form->error($model,'monit_record_activated'); ?>
	</div>

	<div class="row">
		<?php echo $form->labelEx($model,'alert_sms_activated'); ?>
		<?php echo $form->checkbox($model,'alert_sms_activated'); ?>
		<?php echo $form->error($model,'alert_sms_activated'); ?>
	</div>

	<div class="row">
		<?php echo $form->labelEx($model,'alert_email_activated'); ?>
		<?php echo $form->checkbox($model,'alert_email_activated'); ?>
		<?php echo $form->error($model,'alert_email_activated'); ?>
	</div>

	<div class="row buttons">
		<?php echo CHtml::submitButton($model->isNewRecord ? 'Create' : 'Save'); ?>
	</div>

<?php $this->endWidget(); ?>

</div><!-- form -->