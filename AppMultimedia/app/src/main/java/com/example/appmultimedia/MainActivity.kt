package com.example.appmultimedia

// Importaciones necesarias para trabajar con multimedia y UI
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appmultimedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // ViewBinding: permite acceder a los controles del XML sin findViewById
    private lateinit var binding: ActivityMainBinding

    // Objeto MediaPlayer para manejar el audio
    // Se declara nullable porque al inicio no hay audio cargado
    private var mediaPlayer: MediaPlayer? = null

    // Variable para saber si el audio está en pausa
    private var audioPausado = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Separar lógica en funciones (buena práctica)
        configurarAudio()
        configurarVideo()
    }

    // =========================
    // 🔊 CONFIGURACIÓN DE AUDIO
    // =========================
    private fun configurarAudio() {

        // Evento: botón reproducir
        binding.btnPlay.setOnClickListener {

            // Si no existe el reproductor, se crea
            if (mediaPlayer == null) {

                // Cargar el archivo desde res/raw
                mediaPlayer = MediaPlayer.create(this, R.raw.audio_demo)

                // Evento cuando el audio termina
                mediaPlayer?.setOnCompletionListener {
                    binding.tvEstadoAudio.text = "Estado: reproducción finalizada"

                    // Liberar recursos (MUY IMPORTANTE)
                    liberarAudio()
                }
            }

            // Iniciar reproducción
            mediaPlayer?.start()
            audioPausado = false

            binding.tvEstadoAudio.text = "Estado: reproduciendo audio"

            // Mensaje visual al usuario
            Toast.makeText(this, "Reproduciendo audio", Toast.LENGTH_SHORT).show()
        }

        // Evento: botón pausar
        binding.btnPause.setOnClickListener {

            // Validar que el audio esté reproduciéndose
            if (mediaPlayer != null && mediaPlayer!!.isPlaying) {

                mediaPlayer?.pause()
                audioPausado = true

                binding.tvEstadoAudio.text = "Estado: audio pausado"

                Toast.makeText(this, "Audio pausado", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "No hay audio reproduciéndose", Toast.LENGTH_SHORT).show()
            }
        }

        // Evento: botón detener
        binding.btnStop.setOnClickListener {

            if (mediaPlayer != null) {

                // Detener y liberar recursos
                liberarAudio()

                binding.tvEstadoAudio.text = "Estado: detenido"

                Toast.makeText(this, "Audio detenido", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "No hay audio activo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // =========================
    // 🎥 CONFIGURACIÓN DE VIDEO
    // =========================
    private fun configurarVideo() {

        // Crear la ruta del video desde res/raw
        val videoUri = Uri.parse("android.resource://$packageName/${R.raw.video_demo1}")

        // Asignar el video al componente VideoView
        binding.videoView.setVideoURI(videoUri)

        // MediaController agrega controles automáticamente (play, pausa, barra)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)

        binding.videoView.setMediaController(mediaController)





        // Botón para reproducir el video
        binding.btnPlayVideo.setOnClickListener {
            binding.videoView.setOnPreparedListener {
                binding.tvEstadoVideo.text = "Video cargado correctamente"
            }
            binding.videoView.setOnErrorListener { _, what, extra ->
                binding.tvEstadoVideo.text = "Error al cargar video: $what / $extra"
                true
            }
            binding.videoView.start()

            binding.tvEstadoVideo.text = "Video en reproducción"

            Toast.makeText(this, "Reproduciendo video", Toast.LENGTH_SHORT).show()
        }

        // Evento cuando el video termina
        binding.videoView.setOnCompletionListener {
            binding.tvEstadoVideo.text = "Video finalizado"
        }
    }

    // =========================
    // 🧹 LIBERAR RECURSOS AUDIO
    // =========================
    private fun liberarAudio() {

        // Libera memoria usada por MediaPlayer
        mediaPlayer?.release()

        // Evita fugas de memoria
        mediaPlayer = null

        audioPausado = false
    }

    // =========================
    // 🔄 CICLO DE VIDA
    // =========================
    override fun onPause() {
        super.onPause()

        // Si el usuario sale de la app, pausamos el audio
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {

            mediaPlayer?.pause()
            audioPausado = true

            binding.tvEstadoAudio.text = "Estado: audio pausado por ciclo de vida"
        }

        // Pausar el video si está reproduciéndose
        if (binding.videoView.isPlaying) {

            binding.videoView.pause()

            binding.tvEstadoVideo.text = "Video pausado por ciclo de vida"
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // Liberar recursos al cerrar la app
        liberarAudio()

        // Detener video completamente
        binding.videoView.stopPlayback()
    }
}