package br.com.alura.clientelo.reports;

public class RelatorioHelper<T> {
	public static void mostrar(RelatorioMostruario mostruario) {
		mostruario.accept();
	}
}
