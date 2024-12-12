package tren;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	
		Tren[] aTren = new Tren[50];
		int menu;
		Scanner teclado = new Scanner(System.in);

		aTren[0] = new Tren("AVE", "123", 60, 100, 300);
		aTren[1] = new Tren("Cercanias", "124", 100, 100, 400);
		aTren[2] = new Tren("Mercancias", "125", 90, 100, 200);

		do {

			menu = menuMt(teclado);

			switch (menu) {

			case 1:
				introducirTren(teclado, aTren);
				break;
			case 2:
				mostrarPorVelocidad(teclado, aTren);
				break;
			case 3:
				ordenarPorDuracion(aTren);
				break;
			case 4:
				reservarAsiento(teclado, aTren);
				break;
			case 5:
				mostrarTodo(aTren);
				break;

			}

		} while (menu != 6);
		System.out.println("Adios");
		teclado.close();
	}

	public static int menuMt(Scanner teclado) {
		int menu = 0;
		do {
			System.out.println("1. Introducir los trenes. [1p]\r\n"
					+ "2. Mostrar los trenes que superen una velocidad dada. [1,25p]\r\n"
					+ "3. Ordenar los trenes de cercanías por duración del viaje. [1,25p]\r\n"
					+ "4. Reserva de asientos. [1,25p]\r\n"
					+ "5. Mostrar todos los trenes que se han introducido. [1p]\r\n" + "6. Salir");
			menu = teclado.nextInt();
			if (menu < 1 || menu > 6) {
				System.out.println("ERROR");
			}
		} while (menu < 1 || menu > 6);
		return menu;

	}

	public static int contadorPosicion(Tren[] aTren) {
		int posicion = -1;
		boolean encontrado = false;

		for (int i = 0; !encontrado && i < aTren.length; i++) {
			encontrado = false;
			if (aTren[i] == null) {
				encontrado = true;
				posicion = i;
			}
		}

		if (posicion == -1) { // 0=ARRAY VACIA/ .LENGTH=LLENA SE PARA EN EL NULL/ POSICION=PRIMER HUECO NULL
			posicion = aTren.length;
		}

		return posicion;
	}

	public static void introducirTren(Scanner teclado, Tren[] aTren) {

		boolean fallo = false, salir = false;
		String referencia = "", seguir = "", tipo = "";
		int asientos = 0, tipoTren = 0;
		double duracion = 0, kmRecorrido = 0;

		int posicion = contadorPosicion(aTren);

		if (posicion == aTren.length) {
			System.out.println("No hay hueco en la cochera");
			salir = true;
		}
		for (int i = posicion; i < aTren.length && !salir; i++) {

			do {
				fallo = false;
				System.out.print("Ingresa el numero de referencia" + (i) + ": ");
				referencia = teclado.next();

				for (int j = i - 1; j != -1; j--) {
					if (referencia.equalsIgnoreCase(aTren[j].getReferencia())) {
						fallo = true;
						System.out.println("Ya hay un tren con ese numero");
					}
				}
			} while (fallo);

			System.out.print("Ingresa el tipo de tren" + ": \r\n 1-ave\r\n 2-Cercanias\r\n 3-Mercancias\r\n");
			do {
				tipoTren = teclado.nextInt();
				fallo = false;
				if (tipoTren == 1) {
					tipo = "AVE";
				} else if (tipoTren == 2) {
					tipo = "Cercanias";
				} else if (tipoTren == 3) {
					tipo = "Mercancias";
				} else {
					fallo = true;
					System.out.println("No existe ese tipo de tren");
				}
			} while (fallo);

			do {
				System.out.print("Ingresa la duracion del trayecto" + (i) + ": ");
				fallo = false;
				duracion = teclado.nextDouble();
				if (duracion < 0) {
					fallo = true;
					System.out.println("No puede tardar menos de 0 minutos");
				}
			} while (fallo);

			do {
				System.out.print("Ingresa la distancia del trayecto " + (i) + ": ");
				fallo = false;
				kmRecorrido = teclado.nextDouble();
				if (kmRecorrido < 0) {
					fallo = true;
					System.out.println("No puede ser menos de 0 km");
				}
			} while (fallo);

			System.out.print("Ingresa la cantidad de asientos" + (i) + ": ");
			do {
				fallo = false;
				asientos = teclado.nextInt();
				if (asientos > 400) {
					fallo = true;
					System.out.println("No pueden haber mas de 400 asientos");
				}
			} while (fallo);

			aTren[i] = new Tren(tipo, referencia, duracion, kmRecorrido, asientos);

			if (i != aTren.length - 1) {
				do {
					salir = false;
					fallo = false;
					System.out.println("Quieres introducir mas cuentas? -n -s ");
					seguir = teclado.next();
					if (seguir.equalsIgnoreCase("n")) {
						salir = true;
					} else if (seguir.equalsIgnoreCase("s")) {

					} else {
						System.out.println("error");
						fallo = true;
					}
				} while (fallo);

			}
		}
	}

	public static void mostrarPorVelocidad(Scanner teclado, Tren[] aTren) {
		int velocidad = 0;
		boolean salir = false, fallo = false;
		int posicion = contadorPosicion(aTren);

		if (posicion == 0) {
			System.out.println("No hay trenes");
			salir = true;
		} else {

			do {
				System.out.println("Introduce la velocidad");
				velocidad = teclado.nextInt();
				fallo = false;

				if (velocidad < 0) {
					fallo = true;
					System.out.println("la velocidad no puede ser negativa");
				}

			} while (fallo);
		}

		for (int i = posicion - 1; i >= 0 && !salir; i--) {

			if (aTren[i].obtenerVelocidadTotal(aTren[i].getDuracion(), aTren[i].getKmRecorrido()) > velocidad) {

				System.out.println(aTren[i]);

			}

		}
		/*
		 * for (int i = 0; i < aTren.length; i++) { Tren tren = aTren[i];
		 * System.out.println("Trenes con velocidad mayor a " + velocidad); if
		 * (aTren[i].obtenerVelocidadTotal(aTren[i].getDuracion(),
		 * aTren[i].getKmRecorrido()) > velocidad) {
		 * 
		 * System.out.println(aTren[i]); mostrado = true; } } if (!mostrado) {
		 * System.out.println("Esta vacia"); }
		 */
	}

	public static void ordenarPorDuracion(Tren[] aTren) {
		double temp;
		int posicion = contadorPosicion(aTren);

		if (posicion == 0) {
			System.out.println("No hay trenes");

		} else {

			for (int i = 0; i < posicion - 1; i++) {
				for (int j = 0; j < posicion - 1 - i; j++) {
					if (aTren[j].getDuracion() > aTren[j + 1].getDuracion()) {
						// Intercambiar elementos
						temp = aTren[j].getDuracion();
						aTren[j].setDuracion(aTren[j + 1].getDuracion());
						aTren[j + 1].setDuracion(temp);
					}
				}
			}
			for (int i = 0; i < posicion; i++) {
				if (aTren[i].getTipo().equals("Cercanias")) {
					System.out.println(aTren[i]);
				}
			}
		}
	}

	public static void reservarAsiento(Scanner teclado, Tren[] aTren) {
		boolean lleno = false, encontrado = false, salir = false;
		String referencia = "";
		int cantidad = 0, posicion = contadorPosicion(aTren);

		if (posicion == 0) {
			System.out.println("No hay trenes");
		} else {
			do {
				System.out.print("Ingresa el numero de referencia:");
				referencia = teclado.next();
				for (int i = 0; i < posicion; i++) {

					if (referencia.equalsIgnoreCase(aTren[i].getReferencia())) {
						
						lleno = aTren[i].trenLleno();
						
						if (lleno) {
							System.out.println("Este tren esta lleno");
						} else {
							encontrado = true;
							do {
								System.out.println(
										"Cuantos asientos quieres reservar, quedan " + (400 - aTren[i].getAsientos()));
								cantidad = teclado.nextInt();
								if (cantidad > (400 - aTren[i].getAsientos())) {
									System.out.println("Tiene que ser menor que " + (401 - aTren[i].getAsientos()));
								}
							} while (cantidad > (400 - aTren[i].getAsientos()));

							aTren[i].setAsientos(aTren[i].getAsientos() + cantidad);
						}
					}
				}

				if (!encontrado) {
					System.out.println("No hay un tren con ese numero, quieres volver a buscar? -s");
					if (!teclado.next().equalsIgnoreCase("s")) {
						salir = true;
					}
				}

			} while (!encontrado && !salir);

		}

	}

	public static void mostrarTodo(Tren[] aTren) {

		int posicion = contadorPosicion(aTren);

		if (posicion == 0) {
			System.out.println("No hay nada");
		} else {
			for (int i = posicion - 1; i >= 0; i--) {
				System.out.println(aTren[i]);
			}
		}
	}
}
