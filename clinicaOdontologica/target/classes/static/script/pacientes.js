function enviarDatos() {
  console.log("enviarDatos paciente");
    const datosPaciente = {
      nombre: document.getElementById('nombre').value,
      apellido: document.getElementById('apellido').value,
      dni: document.getElementById('dni').value,
      fechaDeIngreso: document.getElementById('fechaDeIngreso').value,
    }

    const urlServicio = 'http://localhost:8081/pacientes/registrar';

    const opciones = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(datosPaciente)
    };

    fetch(urlServicio, opciones)
      .then(response => {
        if (!response.ok) {
          alert ('Error en la solicitud');
          throw new Error('Error en la solicitud al servicio pacientes');
        }
        return response.json();
      })
      .then(data => {
        const id_response = "El ID del paciente " + data.nombre + " " + data.apellido + " es " + data.id;
        alert(id_response);
      })
      .catch(error => {
        console.error('Error:', error);
      });
    }