package com.assignments.todolist.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="todolist")
public class ToDo implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name="TITLE", nullable=false)
	private String title;

	@Column(name="NOTES", nullable=false)
	private String notes;

	@Column(name="STATUS", nullable=false)
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ToDo todo = (ToDo) o;

		if (id != null ? !id.equals(todo.id) : todo.id != null) return false;
		if (title != null ? !title.equals(todo.title) : todo.title != null) return false;
		if (notes != null ? !notes.equals(todo.notes) : todo.notes != null) return false;
		return status != null ? status.equals(todo.status) : todo.status == null;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", title=" + title + ", notes=" + notes
				+ ", status=" + status + "]";
	}


}
