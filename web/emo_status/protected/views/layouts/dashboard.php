<div class="row">
	<div class="col-md-2">
		<ul class="nav nav-pills nav-stacked">
			<li class="active"><a href="#">Resumen</a></li>
			<li><a href="<?php echo Yii::app()->baseUrl.'?r=monitoredUser/create' ?>">Usuarios Monitoreados</a></li>
			<li><a href="#">Configuración</a></li>
		</ul>
	</div>
	<div class="col-md-10">
		<?php
			$client=new SoapClient('http://localhost/emo_status/index.php?r=stock/quote');
			echo $client->getPrice('GOOGLE');
		?>
	</div>
</div>
