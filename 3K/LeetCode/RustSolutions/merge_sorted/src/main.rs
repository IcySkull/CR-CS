use std::ops::Deref;

fn main() {
    let ref mut x = Box::new(ListNode::new(5));
    x.next = Some(Box::new(ListNode::new(6)));
    let ref mut z = x.next;
    z.as_mut().unwrap().next = Some(Box::new(ListNode::new(7)));
    let ref mut y = z.as_mut().unwrap().next;
    y.as_mut().unwrap().next = Some(Box::new(ListNode::new(8)));
    let ref mut w = y.as_mut().unwrap().next;
    w.as_mut().unwrap().next = Some(Box::new(ListNode::new(9)));
    println!("{:?}", x);
}

#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub data: i32,
    pub next: Option<Box<ListNode>>
}

impl ListNode {
    pub fn new(data: i32) -> Self {
        ListNode { data, next: None}
    }
}

pub fn merge_two_lists(list1: Option<Box<ListNode>>, list2: Option<Box<ListNode>>) 
    -> Option<Box<ListNode>> {
    return match (list1, list2) {
        (None, None) => None,
        (list1, None) => list1,
        (None, list2) => list2,
        (mut list1, mut list2) => {
            let ref mut itr1 = list1;
            let ref mut itr2 = list2;
            let list2_head = list2.as_ref();
            while let Some(ref mut node) = itr2 {

            }
            return list1;
        }
    } 
}

fn get_segment_

fn insert_after(mut node: Box<ListNode>, inserted_head: Box<ListNode>, inserted_segment: &mut Box<ListNode>) 
    -> Option<Box<ListNode>> {
    let temp = &inserted_segment.next;
    inserted_segment.next = node.next;
    node.next = Some(inserted_head);
    node.next
}

fn insert_before(mut node: Box<ListNode>, inserted_head: &mut Box<ListNode>, inserted_segment: &mut Box<ListNode>) 
    -> Option<Box<ListNode>> {
    let temp = &inserted_segment.next;
    node.next
}