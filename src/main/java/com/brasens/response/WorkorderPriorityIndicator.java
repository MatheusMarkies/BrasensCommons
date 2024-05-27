package com.brasens.response;

public class WorkorderPriorityIndicator {
	private int Urgente, Alta, Media, Baixa;

	public WorkorderPriorityIndicator() {
	}

	public WorkorderPriorityIndicator(int urgente, int alta, int media, int baixa) {
		Urgente = urgente;
		Alta = alta;
		Media = media;
		Baixa = baixa;
	}

	public int getUrgente() {
		return Urgente;
	}

	public void setUrgente(int urgente) {
		Urgente = urgente;
	}

	public int getAlta() {
		return Alta;
	}

	public void setAlta(int alta) {
		Alta = alta;
	}

	public int getMedia() {
		return Media;
	}

	public void setMedia(int media) {
		Media = media;
	}

	public int getBaixa() {
		return Baixa;
	}

	public void setBaixa(int baixa) {
		Baixa = baixa;
	}
}
