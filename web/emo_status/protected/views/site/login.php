<?php
/* @var $this SiteController */
/* @var $model LoginForm */
/* @var $form CActiveForm  */

$this->pageTitle=Yii::app()->name . ' - Login';
$this->breadcrumbs=array(
	'Login',
	);
	?>

	<h2>Entrar</h2>
	<div class="form">
		<?php $form=$this->beginWidget('CActiveForm', array(
			'id'=>'login-form',
			'enableClientValidation'=>true,
			'clientOptions'=>array(
				'validateOnSubmit'=>true,
				),
				)); ?>

		<p class="note">Los campos con <span class="required">*</span> son requeridos.</p>

		<div class="row">
			<?php echo $form->labelEx($model,'username'); ?>
			<?php echo $form->textField($model,'username'); ?>
		</div>

		<div class="row">
			<?php echo $form->labelEx($model,'password'); ?>
			<?php echo $form->passwordField($model,'password'); ?>
			<?php echo $form->error($model,'form'); ?>
		</div>

		<div class="row rememberMe">
			<?php echo $form->checkBox($model,'rememberMe'); ?>
			<?php echo $form->label($model,'rememberMe'); ?>
			<?php echo $form->error($model,'rememberMe'); ?>
		</div>

		<div class="row buttons">
			<button type="submit" class="btn btn-success">Entrar</button>
		</div>

		<?php $this->endWidget(); ?>
	</div><!-- form -->

	<hr/>

	<div id="create-account">
		<a href="index.php?r=user/create" class="btn btn-primary" role="button">Crear una cuenta nueva</a>
	</div>