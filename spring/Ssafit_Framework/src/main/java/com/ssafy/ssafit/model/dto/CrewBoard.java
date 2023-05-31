package com.ssafy.ssafit.model.dto;

public class CrewBoard {
	private int crew_board_id;
	private int crew_id;
	private String crew_name;
	
	public CrewBoard() {
	}
	
	public CrewBoard(int crew_board_id, int crew_id, String crew_name) {
		super();
		this.crew_board_id = crew_board_id;
		this.crew_id = crew_id;
		this.crew_name = crew_name;
	}

	public int getCrew_board_id() {
		return crew_board_id;
	}
	public void setCrew_board_id(int crew_board_id) {
		this.crew_board_id = crew_board_id;
	}
	public int getCrew_id() {
		return crew_id;
	}
	public void setCrew_id(int crew_id) {
		this.crew_id = crew_id;
	}
	public String getCrew_name() {
		return crew_name;
	}
	public void setCrew_name(String crew_name) {
		this.crew_name = crew_name;
	}

	@Override
	public String toString() {
		return "CrewBoard [crew_board_id=" + crew_board_id + ", crew_id=" + crew_id + ", crew_name=" + crew_name + "]";
	}
	
}
