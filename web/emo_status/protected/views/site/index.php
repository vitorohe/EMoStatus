<?php
/* @var $this SiteController */

$this->pageTitle=Yii::app()->name;
?>

<div id="content">

<?php 
	if (Yii::app()->user->isGuest) {
        $this->renderPartial('/layouts/welcome');
    }
    else {
		$this->renderPartial('/layouts/dashboard');
	}
	
?>

</div>